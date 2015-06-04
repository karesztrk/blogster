package hu.sample.blogster.service.user;

import hu.sample.blogster.entity.user.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public void saveMasterUser();

	public User findMasterUser();
}
