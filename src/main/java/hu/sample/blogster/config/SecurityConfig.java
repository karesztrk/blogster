package hu.sample.blogster.config;

import hu.sample.blogster.entity.user.Role;
import hu.sample.blogster.service.AuthenticationFailureHandler;
import hu.sample.blogster.service.AuthenticationSuccessHandler;
import hu.sample.blogster.service.user.UserService;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.userDetailsService(userService)
			.csrf().disable()
			.authorizeRequests()
				//.antMatchers("/sec/moderation.html").hasRole("MODERATOR")
				.antMatchers("/blog").permitAll()
				.antMatchers("/blog/**").hasAuthority(Role.ADMINISTRATOR.name())
				.antMatchers("/**").permitAll()
			.and()
			.formLogin()
				.loginProcessingUrl("/j_spring_security_check")
				.failureHandler(authenticationFailureHandler)
				.successHandler(authenticationSuccessHandler)
				//.loginPage("/user-login.html")
				//.defaultSuccessUrl("/success-login.html")
				//.failureUrl("/error-login.html")
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/j_spring_security_logout")
				.logoutSuccessUrl("/");
	}
}
