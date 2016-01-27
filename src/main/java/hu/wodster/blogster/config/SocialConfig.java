package hu.wodster.blogster.config;

import hu.wodster.blogster.common.core.UserAccount;
import hu.wodster.blogster.repository.social.SocialConnectionRepository;
import hu.wodster.blogster.service.user.UserService;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SocialConnectionRepository socialConnectionRepository;

	@Autowired
	private SignInAdapter signInAdapter;

	@Autowired
	private ConnectionSignUp connectionSignUp;

	@Autowired
	private UserService userService;

	@Override
	public void addConnectionFactories(final ConnectionFactoryConfigurer cfConfig, final Environment env) {
		// Facebook
		cfConfig.addConnectionFactory(new FacebookConnectionFactory(env.getProperty("social.facebook.clientID"), env
				.getProperty("social.facebook.clientSecret")));
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(
			final ConnectionFactoryLocator connectionFactoryLocator) {

		final hu.wodster.blogster.repository.social.UsersConnectionRepository connectionRepo = new hu.wodster.blogster.repository.social.UsersConnectionRepository(
				connectionFactoryLocator, socialConnectionRepository, Encryptors.noOpText(), userService);

		connectionRepo.setConnectionSignUp(connectionSignUp);
		return connectionRepo;
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	/**
	 * Creates a new connection. Connections are created for each user request.
	 *
	 * @param usersConnectionRepository
	 * @return
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository(final UsersConnectionRepository usersConnectionRepository) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null == authentication) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}

		final UserAccount user = (UserAccount) authentication.getPrincipal();
		return usersConnectionRepository.createConnectionRepository(user.getUsername());
	}

	/**
	 * Builds a Facebook API instance for a user request from the available user
	 * connections.
	 *
	 * @param usersConnectionRepository
	 * @return
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook(final UsersConnectionRepository usersConnectionRepository) {
		final ConnectionRepository connectionRepository = connectionRepository(usersConnectionRepository);
		final Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);

		if (null != connection) {
			return connection.getApi();
		}

		return null;
	}

	@Bean
	public ConnectController connectController(final ConnectionFactoryLocator connectionFactoryLocator,
			final ConnectionRepository connectionRepository) {
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}

	@Bean
	public ProviderSignInController providerSignInController(final ConnectionFactoryLocator connectionFactoryLocator,
			final UsersConnectionRepository connectionRepository) {
		return new ProviderSignInController(connectionFactoryLocator, connectionRepository, signInAdapter);
	}

}
