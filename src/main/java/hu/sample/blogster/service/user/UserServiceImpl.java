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
}
