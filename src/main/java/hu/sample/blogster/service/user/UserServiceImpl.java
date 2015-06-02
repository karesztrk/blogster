package hu.sample.blogster.service.user;

import hu.sample.blogster.entity.user.User;
import hu.sample.blogster.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
		if(StringUtils.isEmpty(email)) {
			throw new IllegalArgumentException();
		}
		return userRepository.findByEmail(email);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void saveMasterUser() {
		User masterUser = userRepository.findByEmail(MASTER_USER_EMAIL);
		if(null == masterUser) {
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
		return user;
	}
}
