package hu.wodster.blogster.repository.user;

import hu.wodster.blogster.model.user.User;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface to manage {@link User} entities.
 *
 * @author Károly
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * Finds the user by his/her email address.
	 *
	 * @param email
	 *            email to be used
	 * @return the found user or null
	 */
	public User findByEmail(final String email);

}
