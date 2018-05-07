package hu.wodster.blogster.service.user;

import hu.wodster.blogster.common.core.UserAccount;
import hu.wodster.blogster.model.user.User;
import hu.wodster.blogster.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl extends AbstractUserService implements UserService {

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

}
