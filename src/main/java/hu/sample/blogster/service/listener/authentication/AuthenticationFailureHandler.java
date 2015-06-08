package hu.sample.blogster.service.listener.authentication;

import hu.sample.blogster.service.listener.AuthenticationEventListener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("authenticationFailureHandler")
public class AuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler implements
		AuthenticationEventListener {

	private static final String AUTH_FAILURE_JSON = "{\"message\":\"Authentication failed\"}";

	private final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationEventListener.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		logAuthentication();

		// check if login is originated from ajax call
		if (isAjaxCall(request)) {
			try {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().print(AUTH_FAILURE_JSON);
				response.getWriter().flush();
			} catch (IOException e) {
				LOGGER.error(
						"An error occurred while writing response to authentication request",
						e);
			}
		} else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}

	@Override
	public void logAuthentication() {
		LOGGER.info("Failed to authenticate user");
	}

	@Override
	public boolean isAjaxCall(HttpServletRequest request) {
		return "true".equals(request.getHeader(AJAX_REQUEST_HEADER_PARAM_NAME));
	}
}