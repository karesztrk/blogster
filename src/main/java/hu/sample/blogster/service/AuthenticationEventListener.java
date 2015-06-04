package hu.sample.blogster.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("authenticationEventListener")
public class AuthenticationEventListener implements
		ApplicationListener<AbstractAuthenticationEvent> {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationEventListener.class);

	@Override
	public void onApplicationEvent(AbstractAuthenticationEvent event) {
		if (event instanceof InteractiveAuthenticationSuccessEvent) {
			// ignores to prevent duplicate logging with
			// AuthenticationSuccessEvent
			return;
		}

		Authentication authentication = event.getAuthentication();
		StringBuilder message = new StringBuilder();
		message.append("Login attempt with username: ");
		message.append(authentication.getName());
		message.append("...");
		message.append(authentication.isAuthenticated() ? "Success" : "Fail");

		logger.info(message.toString());
	}
}
