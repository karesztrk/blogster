package hu.wodster.blogster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Serves the web application related configuration. Basicaly it replaces the
 * web.xml content with Java configuration.
 *
 * @author Károly
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("hu.wodster.blogster.controller")
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

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void configureDefaultServletHandling(
			final DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
