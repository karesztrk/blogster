package hu.wodster.blogster.controller.home;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import hu.wodster.blogster.controller.AbstractControllerTest;
import hu.wodster.blogster.service.blog.PostService;

import org.junit.Test;
import org.mockito.InjectMocks;

public class HomeControllerTest extends AbstractControllerTest {

	@InjectMocks
	private PostService postService;

	@Test
	public void testHome() throws Exception {
		mockMvc.perform(get("/")).andExpect(redirectedUrl("/blog"));
	}

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(redirectedUrl("/blog"));
	}
}
