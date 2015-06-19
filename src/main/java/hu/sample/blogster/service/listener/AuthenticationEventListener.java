package hu.sample.blogster.service.listener;

import java.util.EventListener;

import javax.servlet.http.HttpServletRequest;

/**
 * Base for authentication event observers.
 *
 * @author Károly
 *
 */
public interface AuthenticationEventListener extends EventListener {

	/**
	 * Custom AJAX header to be used during the authentication which can be used
	 * by the client.
	 */
	public static final String AJAX_REQUEST_HEADER_PARAM_NAME = "X-Ajax-call";

	/**
	 * Logs the authentication event.
	 */
	public void logAuthentication();

	/**
	 * Decides whether the current request is an AJAX request.
	 *
	 * @param request
	 *            a HTTP request instance
	 * @return true if AJAX request
	 */
	public boolean isAjaxCall(final HttpServletRequest request);
}
