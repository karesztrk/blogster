package hu.sample.blogster.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("authenticationFailureHandler")
public class AuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	private static final String AUTH_FAILURE_JSON = "{\"message\":\"Authentication failed\"}";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		// check if login is originated from ajax call
		if ("true".equals(request.getHeader("X-Ajax-call"))) {
			try {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().print(AUTH_FAILURE_JSON);
				response.getWriter().flush();
			} catch (IOException e) {
				// handle exception...
			}
		} else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}
}
