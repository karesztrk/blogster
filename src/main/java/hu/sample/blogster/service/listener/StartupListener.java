package hu.sample.blogster.service.listener;

import hu.sample.blogster.service.blog.PostService;
import hu.sample.blogster.service.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements
		ApplicationListener<ContextRefreshedEvent> {

	private final Logger LOGGER = LoggerFactory
			.getLogger(StartupListener.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		LOGGER.info("Application started");
		userService.saveMasterUser();
		postService.saveDemoPost();
	}
}
