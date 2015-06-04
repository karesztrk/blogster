package hu.sample.blogster.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		// check if login is originated from ajax call
		if ("true".equals(request.getHeader("X-Ajax-call"))) {
			try {
				response.getWriter().print("success");
				response.getWriter().flush();
			} catch (IOException e) {
				// handle exception...
			}
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
