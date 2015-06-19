package hu.sample.blogster.service.user;

import hu.sample.blogster.model.user.User;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Business interface of {@link User} management. It is also a base for the user
 * authentication.
 *
 * @author Károly
 *
 */
public interface UserService extends UserDetailsService {

	/**
	 * Saves one or more user for demo purposes.
	 */
	public void saveMasterUser();

	/**
	 * Finds the master user of the application.
	 *
	 * @return the master user if exists or null
	 */
	public User findMasterUser();

	/**
	 * Saves or updates a user.
	 *
	 * @param user
	 *            a user
	 * @return the persisted user instance
	 */
	public User save(final User user);

	/**
	 * Finds a managed user by his/her email address.
	 *
	 * @param email
	 *            address to be looked for
	 * @return the user or null
	 */
	public User findUser(final String email);
}
