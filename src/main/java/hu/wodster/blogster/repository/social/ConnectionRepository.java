package hu.wodster.blogster.repository.social;

import hu.wodster.blogster.model.social.SocialConnection;
import hu.wodster.blogster.model.social.SocialNetwork;
import hu.wodster.blogster.model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Repository class for managing {@link Connection} for social users.
 *
 * @author Károly
 */
public class ConnectionRepository implements
		org.springframework.social.connect.ConnectionRepository {

	private final User user;

	/**
	 * Service locator for {@link ConnectionFactory}.
	 */
	private final ConnectionFactoryLocator connectionFactoryLocator;

	/**
	 * Service to encrypt sensitive data.
	 */
	private final TextEncryptor textEncryptor;

	/**
	 * Local repository interface to manage social connections.
	 */
	private final SocialConnectionRepository socialConnectionRepository;

	/**
	 * Creates a new connection repository with the mandatory attributes.
	 *
	 * @param userId
	 * @param connectionFactoryLocator
	 * @param socialConnectionRepository
	 * @param encryptor
	 */
	public ConnectionRepository(final User user,
			final ConnectionFactoryLocator connectionFactoryLocator,
			final SocialConnectionRepository socialConnectionRepository,
			final TextEncryptor encryptor) {
		this.user = user;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.socialConnectionRepository = socialConnectionRepository;
		this.textEncryptor = encryptor;
	}

	@Override
	public MultiValueMap<String, Connection<?>> findAllConnections() {

		final List<SocialConnection> socialConnections = socialConnectionRepository
				.findByUserOrderByNetworkAscRankAsc(user);

		final MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();

		for (final String registeredProviderId : connectionFactoryLocator
				.registeredProviderIds()) {
			connections.put(registeredProviderId,
					Collections.<Connection<?>> emptyList());
		}

		for (final SocialConnection connection : socialConnections) {
			final String providerId = connection.getNetwork().name();

			if (connections.get(providerId).isEmpty()) {
				connections.put(providerId, new LinkedList<Connection<?>>());
			}
			connections.add(providerId,
					createConnection(providerId, connection));
		}
		return connections;
	}

	@Override
	public List<Connection<?>> findConnections(final String providerId) {

		final List<Connection<?>> connections = new ArrayList<Connection<?>>();
		final List<SocialConnection> socialConnections = socialConnectionRepository
				.findByUserAndNetworkOrderByRankAsc(user,
						getNetwork(providerId));

		for (final SocialConnection socialConnection : socialConnections) {
			connections.add(createConnection(providerId, socialConnection));
		}

		return connections;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <A> List<Connection<A>> findConnections(final Class<A> apiType) {
		final String providerId = getNetwork(apiType).name();
		final List<?> connections = findConnections(providerId);
		return (List<Connection<A>>) connections;
	}

	@Override
	public MultiValueMap<String, Connection<?>> findConnectionsToUsers(
			final MultiValueMap<String, String> providerUserIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection<?> getConnection(final ConnectionKey connectionKey) {
		final SocialConnection connection = socialConnectionRepository
				.findByUserAndNetworkAndProviderUserId(user,
						getNetwork(connectionKey.getProviderId()),
						connectionKey.getProviderUserId());

		return createConnection(connectionKey.getProviderId(), connection);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <A> Connection<A> getConnection(final Class<A> apiType,
			final String providerUserId) {
		final String providerId = getNetwork(apiType).name();
		return (Connection<A>) getConnection(new ConnectionKey(providerId,
				providerUserId));
	}

	@Override
	public <A> Connection<A> getPrimaryConnection(final Class<A> apiType) {
		final Connection<A> connection = searchPrimaryConnection(apiType);
		if (null == connection) {
			throw new NotConnectedException(getNetwork(apiType).name());
		}

		return connection;
	}

	@Override
	public <A> Connection<A> findPrimaryConnection(final Class<A> apiType) {
		return searchPrimaryConnection(apiType);
	}

	@Override
	@Transactional
	public void addConnection(final Connection<?> connection) {

		final SocialConnection socialConn = new SocialConnection();
		setConnectionData(socialConn, connection.createData());

		socialConnectionRepository.save(socialConn);
	}

	@Override
	public void updateConnection(final Connection<?> connection) {
		final ConnectionData data = connection.createData();
		final SocialConnection socialConn = socialConnectionRepository
				.findByUserAndNetworkAndProviderUserId(user,
						getNetwork(data.getProviderId()),
						data.getProviderUserId());
		setConnectionData(socialConn, data);

		socialConnectionRepository.save(socialConn);
	}

	@Override
	public void removeConnections(final String providerId) {
		socialConnectionRepository.deleteByNetwork(getNetwork(providerId
				.toUpperCase()));
	}

	@Override
	public void removeConnection(final ConnectionKey connectionKey) {
		socialConnectionRepository.deleteByUserAndNetworkAndProviderUserId(
				user, getNetwork(connectionKey.getProviderId()),
				connectionKey.getProviderUserId());
	}

	/**
	 * Searches the primary connection for a specific social API. The order is
	 * defined by the rank of the connection.
	 *
	 * @param apiType
	 * @return
	 */
	private <A> Connection<A> searchPrimaryConnection(final Class<A> apiType) {

		final SocialNetwork network = getNetwork(apiType);
		final SocialConnection connection = socialConnectionRepository
				.findTopByUserAndNetworkOrderByRankAsc(user, network);

		return createConnection(apiType, connection);
	}

	/**
	 * Creates a new connection instance from an application specific instance.
	 *
	 * @param apiType
	 * @param connection
	 * @return
	 */
	private <A> Connection<A> createConnection(final Class<A> apiType,
			final SocialConnection connection) {
		final ConnectionFactory<A> factory = connectionFactoryLocator
				.getConnectionFactory(apiType);
		return factory.createConnection(createConnectionData(connection));
	}

	/**
	 * Creates a new connection instance from an application specific instance.
	 *
	 * @param providerId
	 * @param connection
	 * @return
	 */
	private Connection<?> createConnection(final String providerId,
			final SocialConnection connection) {
		final ConnectionFactory<?> factory = connectionFactoryLocator
				.getConnectionFactory(providerId);
		return factory.createConnection(createConnectionData(connection));
	}

	/**
	 * Copies all data into the local connection instance.
	 *
	 * @param connection
	 * @param data
	 */
	private void setConnectionData(final SocialConnection connection,
			final ConnectionData data) {
		connection.setUser(user);
		connection.setNetwork(getNetwork(data.getProviderId()));
		connection.setProviderUserId(data.getProviderUserId());
		connection.setDisplayName(data.getDisplayName());
		connection.setProfileUrl(data.getProfileUrl());
		connection.setImageUrl(data.getImageUrl());
		connection.setAccessToken(encrypt(data.getAccessToken()));
		connection.setSecret(encrypt(data.getSecret()));
		connection.setRefreshToken(encrypt(data.getRefreshToken()));
		connection.setExpireTime(data.getExpireTime());
	}

	/**
	 * Builds a connection data instance from the local specification.
	 *
	 * @param connection
	 * @return
	 */
	private ConnectionData createConnectionData(
			final SocialConnection connection) {

		final Long expireTime = connection.getExpireTime() == null
				|| connection.getExpireTime() == 0 ? null : connection
				.getExpireTime();
		return new ConnectionData(connection.getNetwork().name(),
				connection.getProviderUserId(), connection.getDisplayName(),
				connection.getProfileUrl(), connection.getImageUrl(),
				decrypt(connection.getAccessToken()),
				decrypt(connection.getSecret()),
				decrypt(connection.getRefreshToken()), expireTime);
	}

	/**
	 * Decrypts a text.
	 *
	 * @param encryptedText
	 * @return
	 */
	private String decrypt(final String encryptedText) {
		return encryptedText != null ? textEncryptor.decrypt(encryptedText)
				: encryptedText;
	}

	/**
	 * Encrypts a text.
	 *
	 * @param text
	 * @return
	 */
	private String encrypt(final String text) {
		return text != null ? textEncryptor.encrypt(text) : text;
	}

	/**
	 * Extracts the social network type for an API type.
	 *
	 * @param apiType
	 * @return
	 */
	private <A> SocialNetwork getNetwork(final Class<A> apiType) {
		final String providerId = connectionFactoryLocator
				.getConnectionFactory(apiType).getProviderId();
		return getNetwork(providerId);
	}

	/**
	 * Extracts the social network type from a provider identifier.
	 *
	 * @param providerId
	 * @return
	 */
	private static SocialNetwork getNetwork(final String providerId) {
		return SocialNetwork.valueOf(providerId.toUpperCase());
	}

}
