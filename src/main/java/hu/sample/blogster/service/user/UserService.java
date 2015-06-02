package hu.sample.blogster.service.user;

import hu.sample.blogster.entity.user.User;

public interface UserService {

	public void saveMasterUser();
	
	public User findMasterUser();
}
