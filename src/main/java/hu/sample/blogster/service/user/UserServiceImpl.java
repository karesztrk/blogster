package hu.sample.blogster.service.user;

import hu.sample.blogster.common.core.UserAccount;
import hu.sample.blogster.model.user.Role;
import hu.sample.blogster.model.user.User;
import hu.sample.blogster.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final String MASTER_USER_EMAIL = "torok.karoly.krisztian@gmail.com";

	@Autowired
	private UserRepository userRepository;

	public User findUser(String email) {
		if (StringUtils.isEmpty(email)) {
			throw new IllegalArgumentException();
		}
		return userRepository.findByEmail(email);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void saveMasterUser() {
		User masterUser = userRepository.findByEmail(MASTER_USER_EMAIL);
		if (null == masterUser) {
			userRepository.save(createMasterUser());
		}
	}

	public User findMasterUser() {
		return findUser(MASTER_USER_EMAIL);
	}

	private User createMasterUser() {
		User user = new User();
		user.setEmail("torok.karoly.krisztian@gmail.com");
		user.setName("Károly Török");
		user.setPassword("");
		user.setRole(Role.ADMINISTRATOR);
		return user;
	}

	@Override
	public UserAccount loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);

		if (null == user) {
			throw new UsernameNotFoundException("The given username not found");
		}

		return new UserAccount(user.getEmail(), user.getPassword(),
				getAuthority(user));
	}

	private static GrantedAuthority getAuthority(User user) {
		return new SimpleGrantedAuthority(
				null == user.getRole() ? Role.USER.name() : user.getRole()
						.name());
	}
}
