package hu.wodster.blogster.repository.social;

import hu.wodster.blogster.model.social.SocialConnection;
import hu.wodster.blogster.model.social.SocialNetwork;
import hu.wodster.blogster.model.user.User;
import hu.wodster.blogster.service.user.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.util.StringUtils;

/**
 * {@link UsersConnectionRepository} that uses the JPA API and the local domain
 * model to persist connection data to a relational database.
 *
 * @author Károly
 */
public class UsersConnectionRepository implements
		org.springframework.social.connect.UsersConnectionRepository {

	/**
	 * Service to encrypt sensitive data.
	 */
	private final TextEncryptor encryptor;

	/**
	 * Service locator for {@link ConnectionFactory}.
	 */
	private final ConnectionFactoryLocator connectionFactoryLocator;

	/**
	 * Local repository interface to manage social connections.
	 */
	private final SocialConnectionRepository socialConnectionRepository;

	/**
	 * The command to execute to create a new local user profile in the event no
	 * user id could be mapped to a connection. Allows for implicitly creating a
	 * user profile from connection data during a provider sign-in attempt.
	 * Defaults to null, indicating explicit sign-up will be required to
	 * complete the provider sign-in attempt.
	 * 
	 * @param connectionSignUp
	 *            a {@link ConnectionSignUp} object
	 * @see #findUserIdsWithConnection(Connection)
	 */
	private ConnectionSignUp connectionSignUp;

	/**
	 * Local user service.
	 */
	private final UserService userService;

	public UsersConnectionRepository(
			final ConnectionFactoryLocator connectionFactoryLocator,
			final SocialConnectionRepository socialConnectionRepository,
			final TextEncryptor encryptor, final UserService userService) {
		this.encryptor = encryptor;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.socialConnectionRepository = socialConnectionRepository;
		this.userService = userService;
	}

	@Override
	public List<String> findUserIdsWithConnection(final Connection<?> connection) {
		final ConnectionKey key = connection.getKey();
		final SocialNetwork network = SocialNetwork.valueOf(key.getProviderId()
				.toUpperCase());

		final List<SocialConnection> socialConnections = socialConnectionRepository
				.findByNetworkAndProviderUserId(network,
						key.getProviderUserId());

		if (socialConnections.isEmpty() && null != connectionSignUp) {
			final String newUserId = connectionSignUp.execute(connection);
			if (null != newUserId) {
				createConnectionRepository(newUserId).addConnection(connection);
				return Arrays.asList(newUserId);
			}
		}

		final List<String> userIds = new ArrayList<String>();
		for (final SocialConnection socialConnection : socialConnections) {
			userIds.add(socialConnection.getUser().getEmail());
		}
		return userIds;
	}

	@Override
	public Set<String> findUserIdsConnectedTo(final String providerId,
			final Set<String> providerUserIds) {
		final SocialNetwork network = SocialNetwork.valueOf(providerId
				.toUpperCase());

		final List<SocialConnection> connections = socialConnectionRepository
				.findByNetworkAndProviderUserIdIn(network, providerUserIds);

		final Set<String> userIds = new HashSet<String>();
		for (final SocialConnection connection : connections) {
			userIds.add(connection.getUser().getId().toString());
		}
		return null;
	}

	@Override
	public ConnectionRepository createConnectionRepository(final String userId) {

		if (StringUtils.isEmpty(userId)) {
			throw new IllegalArgumentException("userId cannot be empty");
		}

		return createConnectionRepository(userService.findUser(userId));
	}

	/**
	 * Create a single-user ConnectionRepository instance for the user.
	 *
	 * @param user
	 * @return
	 */
	public ConnectionRepository createConnectionRepository(final User user) {
		if (null == user) {
			throw new IllegalArgumentException("user cannot be null");
		}

		return new ConnectionRepository(user, connectionFactoryLocator,
				socialConnectionRepository, encryptor);
	}

	/**
	 * The command to execute to create a new local user profile in the event no
	 * user id could be mapped to a connection. Allows for implicitly creating a
	 * user profile from connection data during a provider sign-in attempt.
	 * Defaults to null, indicating explicit sign-up will be required to
	 * complete the provider sign-in attempt.
	 *
	 * @param connectionSignUp
	 *            a {@link ConnectionSignUp} object
	 * @see #findUserIdsWithConnection(Connection)
	 */
	public void setConnectionSignUp(final ConnectionSignUp connectionSignUp) {
		this.connectionSignUp = connectionSignUp;
	}

}
