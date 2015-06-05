package hu.sample.blogster.service.listener;

import hu.sample.blogster.service.blog.PostService;
import hu.sample.blogster.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ApplicationEvent>{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		userService.saveMasterUser();
		postService.saveDemoPost();
	}	

	
}
