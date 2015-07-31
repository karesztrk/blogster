package hu.wodster.blogster.service.user;

import hu.wodster.blogster.model.user.Role;
import hu.wodster.blogster.model.user.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Holds common business logic for user service implementations.
 *
 * @author Károly
 */
public class AbstractUserService {

	/**
	 * Master user email address. Unique identifier of the master user.
	 */
	protected static final String MASTER_USER_EMAIL = "torok.karoly.krisztian@gmail.com";

	/**
	 * Extract the Spring Security authorities from a managed user instance.
	 *
	 * @param user
	 *            a user
	 * @return a granted authority (cannot be null)
	 */
	protected static GrantedAuthority[] getAuthority(final User user) {

		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		// Get the primary role
		final Role userRole = null == user.getRole() ? Role.USER : user
				.getRole();
		authorities.add(new SimpleGrantedAuthority(userRole.name()));

		// ... and all sub roles
		for (final Role sub : userRole.getSubRoles()) {
			authorities.add(new SimpleGrantedAuthority(sub.name()));
		}

		final GrantedAuthority[] authArray = new GrantedAuthority[authorities
				.size()];
		return authorities.toArray(authArray);
	}

	/**
	 * Builds the master user instance.
	 *
	 * @return
	 */
	protected static User createMasterUser() {
		final User user = new User();
		user.setEmail("torok.karoly.krisztian@gmail.com");
		user.setName("Károly Török");
		user.setPassword("");
		user.setRole(Role.ADMINISTRATOR);
		return user;
	}
}
