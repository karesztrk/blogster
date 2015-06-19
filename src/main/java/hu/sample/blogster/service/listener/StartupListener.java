package hu.sample.blogster.service.listener;

import hu.sample.blogster.service.blog.PostService;
import hu.sample.blogster.service.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * The service listens for the startup event of the application. Typically
 * happen after the server boot.
 *
 * @author Károly
 *
 */
@Component
public class StartupListener implements
		ApplicationListener<ContextRefreshedEvent> {

	/**
	 * Logger.
	 */
	private final Logger LOGGER = LoggerFactory
			.getLogger(StartupListener.class);

	/**
	 * User manager service.
	 */
	@Autowired
	private UserService userService;

	/**
	 * Post manager service.
	 */
	@Autowired
	private PostService postService;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		LOGGER.info("Application started");
		userService.saveMasterUser();
		postService.saveDemoPost();
	}
}
