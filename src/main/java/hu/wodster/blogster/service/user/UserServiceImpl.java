package hu.wodster.blogster.service.user;

import hu.wodster.blogster.common.core.UserAccount;
import hu.wodster.blogster.model.user.Role;
import hu.wodster.blogster.model.user.User;
import hu.wodster.blogster.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Business service implementation of the {@link User} management.
 *
 * @author Károly
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	/**
	 * Master user email address. Unique identifier of the master user.
	 */
	private static final String MASTER_USER_EMAIL = "torok.karoly.krisztian@gmail.com";

	/**
	 * User repository.
	 */
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUser(final String email) {
		if (StringUtils.isEmpty(email)) {
			throw new IllegalArgumentException();
		}
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(final User user) {
		return userRepository.save(user);
	}

	@Override
	public void saveMasterUser() {
		final User masterUser = userRepository.findByEmail(MASTER_USER_EMAIL);
		if (null == masterUser) {
			userRepository.save(createMasterUser());
		}
	}

	@Override
	public User findMasterUser() {
		return findUser(MASTER_USER_EMAIL);
	}

	@Override
	public UserAccount loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		final User user = userRepository.findByEmail(username);

		if (null == user) {
			throw new UsernameNotFoundException("The given username not found");
		}

		return new UserAccount(user.getEmail(), user.getPassword(),
				getAuthority(user));
	}

	/**
	 * Extract the Spring Security authorities from a managed user instance.
	 *
	 * @param user
	 *            a user
	 * @return a granted authority (cannot be null)
	 */
	private static GrantedAuthority getAuthority(final User user) {
		return new SimpleGrantedAuthority(
				null == user.getRole() ? Role.USER.name() : user.getRole()
						.name());
	}

	/**
	 * Builds the master user instance.
	 *
	 * @return
	 */
	private static User createMasterUser() {
		final User user = new User();
		user.setEmail("torok.karoly.krisztian@gmail.com");
		user.setName("Károly Török");
		user.setPassword("");
		user.setRole(Role.ADMINISTRATOR);
		return user;
	}
}
