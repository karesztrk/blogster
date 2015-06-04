package hu.sample.blogster.controller.user;

import hu.sample.blogster.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

	// private static final Logger logger = LoggerFactory
	// .getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

}
