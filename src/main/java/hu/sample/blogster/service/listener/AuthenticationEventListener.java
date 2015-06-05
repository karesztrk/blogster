package hu.sample.blogster.service.listener;

import java.util.EventListener;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationEventListener extends EventListener {

	public static final String AJAX_REQUEST_HEADER_PARAM_NAME = "X-Ajax-call";

	public void logAuthentication();

	public boolean isAjaxCall(HttpServletRequest request);
}
