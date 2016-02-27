package hu.wodster.blogster.config;

import hu.wodster.blogster.model.blog.Post;
import hu.wodster.blogster.repository.user.UserRepository;
import hu.wodster.blogster.service.blog.PostService;
import hu.wodster.blogster.service.blog.TagService;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

	public TestConfiguration() {
		MockitoAnnotations.initMocks(this); // This is a key
	}

	@Bean
	public PostService postService() {
		final Post post = new Post();
		post.setTitle("Dummy");
		post.setId(1L);

		final PostService service = Mockito.mock(PostService.class);
		Mockito.when(service.find("dummy")).thenReturn(post);

		return service;
	}

	@Bean
	public TagService tagervice() {
		return Mockito.mock(TagService.class);
	}

	@Bean
	public UserRepository userRepository() {
		return Mockito.mock(UserRepository.class);
	}
}
