package hu.sample.blogster.service.user;

import hu.sample.blogster.model.user.Role;
import hu.sample.blogster.model.user.User;
import hu.sample.blogster.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);

		if (null == user) {
			throw new UsernameNotFoundException("The given username not found");
		}

		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), getAuthorities(user));
	}

	private static List<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
				null == user.getRole() ? Role.USER.name() : user.getRole()
						.name());
		authorities.add(authority);
		return authorities;
	}
}
