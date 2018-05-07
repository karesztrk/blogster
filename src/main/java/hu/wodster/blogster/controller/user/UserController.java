package hu.wodster.blogster.controller.user;

import hu.wodster.blogster.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	/**
	 * Displays the about page.
	 *
	 * @return view
	 */
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}
}
