package hu.wodster.blogster.controller.blog;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import hu.wodster.blogster.controller.AbstractControllerTest;

import org.junit.Ignore;
import org.junit.Test;

public class BlogControllerTest extends AbstractControllerTest {

	@Test
	@Ignore
	public void testAddView() throws Exception {
		mockMvc.perform(get("/blog/add")).andExpect(status().isOk())
				.andExpect(model().attributeExists("post"))
				.andExpect(view().name("blog/add"));
	}

	@Test
	@Ignore
	public void testGetView_existing() throws Exception {
		mockMvc.perform(get("/blog/dummy"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("post"))
				.andExpect(
						model().attribute("post",
								hasProperty("id", notNullValue())))
				.andExpect(view().name("blog/view"));

	}

	@Test
	@Ignore
	public void testGetView_notExists() throws Exception {
		mockMvc.perform(get("/blog/notExists")).andExpect(status().isOk())
				.andExpect(model().attributeDoesNotExist("post"))
				.andExpect(view().name("blog/view"));

	}
}
