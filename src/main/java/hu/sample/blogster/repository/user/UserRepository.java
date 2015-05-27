package hu.sample.blogster.repository.user;

import hu.sample.blogster.entity.user.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByEmail(String email);
	
}
