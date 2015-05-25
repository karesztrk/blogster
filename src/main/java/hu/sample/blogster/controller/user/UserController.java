package hu.sample.blogster.controller.user;

import hu.sample.blogster.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
}
