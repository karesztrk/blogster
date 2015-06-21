package hu.wodster.blogster.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Registers the Spring security in the application, namely
 * springSecurityFilterChain.
 *
 * @author Károly
 *
 */
@Order(value = 2)
public class SecurityInitializer extends
		AbstractSecurityWebApplicationInitializer {

}
