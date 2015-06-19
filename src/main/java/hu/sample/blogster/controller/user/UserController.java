package hu.sample.blogster.controller.user;

import hu.sample.blogster.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller of managing {@link User Users}. Handles request for the
 * application users.
 *
 * @author Károly
 *
 */
@Controller
public class UserController {

	/**
	 * User repository.
	 */
	@Autowired
	private UserRepository userRepository;

}
