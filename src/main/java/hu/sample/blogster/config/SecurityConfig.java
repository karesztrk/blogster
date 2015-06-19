package hu.sample.blogster.config;

import hu.sample.blogster.model.user.Role;
import hu.sample.blogster.service.listener.authentication.AuthenticationFailureHandler;
import hu.sample.blogster.service.listener.authentication.AuthenticationSuccessHandler;
import hu.sample.blogster.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Security related configuration component.
 * 
 * @author Károly
 */
@Configuration
@EnableWebMvcSecurity
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

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.userDetailsService(userService)
				// CSRF settings
				.csrf().disable()
				// Authorization settings
				.authorizeRequests()
					// Blog posting
					.antMatchers("/blog/add", "/blog/**/edit").hasAuthority(Role.ADMINISTRATOR.name())
					// Anything else...
					.antMatchers("/**").permitAll()
				.and()
				// Authentication
				.formLogin()
					.loginProcessingUrl("/j_spring_security_check")
					.failureHandler(authenticationFailureHandler)
					.successHandler(authenticationSuccessHandler)
					.permitAll()
				.and()
				// De-authentication
				.logout()
					.logoutUrl("/j_spring_security_logout")
					.logoutSuccessUrl("/");
	}
}
