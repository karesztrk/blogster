package hu.wodster.blogster.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import hu.wodster.blogster.config.TestConfiguration;
import hu.wodster.blogster.config.WebAppConfig;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebAppConfig.class, TestConfiguration.class })
@ActiveProfiles("development")
public abstract class AbstractControllerTest {

	@Autowired
	private WebApplicationContext context;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(context).build();
	}

}
