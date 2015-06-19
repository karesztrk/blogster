package hu.sample.blogster.service.listener.authentication;

import hu.sample.blogster.service.listener.AuthenticationEventListener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Authentication success listener.
 *
 * @author Károly
 *
 */
@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler implements
		AuthenticationEventListener {

	/**
	 * HTTP response body to be send back to the client if the authentication
	 * succeeded.
	 */
	private static final String AUTH_SUCCESS_JSON = "{\"message\":\"Authentication succeded\"}";

	/**
	 * The logger.
	 */
	private final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationEventListener.class);

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException,
			ServletException {

		logAuthentication();

		// check if login is originated from ajax call
		if (isAjaxCall(request)) {
			try {
				response.setStatus(HttpStatus.OK.value());
				response.getWriter().print(AUTH_SUCCESS_JSON);
				response.getWriter().flush();
			} catch (final IOException e) {
				LOGGER.error(
						"An error occurred while writing response to authentication request",
						e);
			}
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

	@Override
	public void logAuthentication() {
		LOGGER.info("Successfully authenticated user");
	}

	@Override
	public boolean isAjaxCall(final HttpServletRequest request) {
		return "true".equals(request.getHeader(AJAX_REQUEST_HEADER_PARAM_NAME));
	}
}
