package hu.sample.blogster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("hu.sample.blogster.controller")
// Uncomment if want to use XML based configuration instead of Java
// @ImportResource("classpath:spring-security.xml")
public class WebAppConfig extends WebMvcConfigurerAdapter {

	/**
	 * Exposes the view resolver to Spring.
	 *
	 * @return
	 */
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		final UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**", "").addResourceLocations(
				"/WEB-INF/resources/");
	}
}
