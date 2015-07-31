package hu.wodster.blogster.config;

import hu.wodster.blogster.model.user.Role;
import hu.wodster.blogster.service.listener.authentication.AuthenticationFailureHandler;
import hu.wodster.blogster.service.listener.authentication.AuthenticationSuccessHandler;
import hu.wodster.blogster.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

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
				// For blog viewing and authentication
				.antMatchers("/blog/*", "/auth/**", "/login", "signin/**")
				.permitAll()
				.and()
				// Authentication
				.formLogin().loginPage("/login").permitAll()
				.loginProcessingUrl("/j_spring_security_check")
				.failureHandler(authenticationFailureHandler)
				.successHandler(authenticationSuccessHandler).permitAll().and()
				// De-authentication
				.logout().logoutUrl("/j_spring_security_logout")
				.logoutSuccessUrl("/")
				// Adds the SocialAuthenticationFilter to Spring Security's
				// filter chain.
				.and().apply(new SpringSocialConfigurer());
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(
				passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
