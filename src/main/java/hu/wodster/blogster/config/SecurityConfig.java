package hu.wodster.blogster.config;

import hu.wodster.blogster.model.user.Role;
import hu.wodster.blogster.service.listener.authentication.AuthenticationFailureHandler;
import hu.wodster.blogster.service.listener.authentication.AuthenticationSuccessHandler;
import hu.wodster.blogster.service.user.AccessTokenService;
import hu.wodster.blogster.service.user.AccessTokenServiceImpl;
import hu.wodster.blogster.service.user.UserService;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Security related configuration component.
 *
 * @author Károly
 */
@Configuration
@EnableWebMvcSecurity
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * User service to authenticate
	 */
	@Autowired
	private UserService userService;

	/**
	 * Authentication success listener.
	 */
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	/**
	 * Authentication failure listener.
	 */
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private OAuth2ClientContextFilter oauthClientFilter;

	@Autowired
	private OAuth2ClientAuthenticationProcessingFilter facebookAuthenticationFilter;

	@Autowired
	private OAuth2ClientContext oauthClientContext;

	@Autowired
	private Environment env;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.userDetailsService(userService)
				// CSRF settings
				.csrf()
				.disable()

				// Authorization settings
				.authorizeRequests()
				// Blog posting
				.antMatchers("/blog/add", "/blog/**/edit")
				.hasAuthority(Role.ADMINISTRATOR.name())
				// For blog viewing
				.antMatchers("/blog/*")
				.permitAll()
				// For OAuth signin
				.antMatchers("/signin/*")
				.permitAll()

				// For facebook auth
				.antMatchers("/facebook/*")
				.hasAuthority(Role.USER.name())
				.and()

				// Authentication
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.loginProcessingUrl("/j_spring_security_check")
				.failureHandler(authenticationFailureHandler)
				.successHandler(authenticationSuccessHandler)
				.permitAll()
				.and()
				// De-authentication
				.logout()
				.logoutUrl("/j_spring_security_logout")
				.logoutSuccessUrl("/")
				.and()
				.addFilterAfter(oauthClientFilter,
						ExceptionTranslationFilter.class)
				.addFilterBefore(facebookAuthenticationFilter,
						FilterSecurityInterceptor.class);
	}

	/**
	 * This authentication entry point is used for all the unauthenticated or
	 * unauthorised sessions to be directed to the /facebookSignIn URL which is
	 * then intercepted by the oAuth2AuthenticationProcessingFilter to trigger
	 * authentication from Facebook.
	 *
	 * @return
	 */
	@Bean
	public AuthenticationEntryPoint createFacebookLoginAuthEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/facebookLogin");
	}

	@Bean
	@Scope("session")
	public OAuth2ProtectedResourceDetails facebook() {
		final AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
		details.setId("facebook");
		details.setClientId(env.getRequiredProperty("social.facebook.clientID"));
		details.setClientSecret(env
				.getRequiredProperty("social.facebook.clientSecret"));
		details.setAccessTokenUri(env
				.getRequiredProperty("social.facebook.accessTokenUri"));
		details.setUserAuthorizationUri(env
				.getRequiredProperty("social.facebook.userAuthorizationUri"));
		details.setTokenName("oauth_token");
		details.setAuthenticationScheme(AuthenticationScheme.query);
		details.setClientAuthenticationScheme(AuthenticationScheme.form);
		return details;
	}

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
	public OAuth2RestOperations facebookRestTemplate() {
		final OAuth2RestTemplate template = new OAuth2RestTemplate(facebook(),
				oauthClientContext);

		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(
				MediaType.APPLICATION_JSON,
				MediaType.valueOf("text/javascript")));

		template.setMessageConverters(Arrays
				.<HttpMessageConverter<?>> asList(converter));

		// final AccessTokenProviderChain provider = new
		// AccessTokenProviderChain(
		// Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
		// provider.setClientTokenServices(clientTokenServices());
		// template.setAccessTokenProvider(provider);
		return template;
	}

	@Bean
	public AccessTokenService clientTokenServices() {
		return new AccessTokenServiceImpl();
	}

	@Bean
	public OAuth2ClientAuthenticationProcessingFilter facebookAuthenticationFilter() {
		final OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(
				"/facebookLogin");
		filter.setRestTemplate(facebookRestTemplate());
		filter.setTokenServices(createFacebookTokenService());
		return filter;
	}

	@Bean
	public ResourceServerTokenServices createFacebookTokenService() {
		final DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(clientTokenServices());
		return tokenServices;
	}
}
