package hu.wodster.blogster.service.social;

import hu.wodster.blogster.model.user.User;
import hu.wodster.blogster.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Application specific one-click sign up adapter. <strong>Prohibited
 * yet</strong>
 *
 * @author Károly
 *
 */
@Service
public class ConnectionSignUp implements
		org.springframework.social.connect.ConnectionSignUp {

	/**
	 * Social user details service.
	 */
	@Autowired
	private SocialUserDetailsService socialUserDetailsService;

	/**
	 * User repository.
	 */
	@Autowired
	private UserRepository userRepository;

	@Override
	public String execute(final Connection<?> connection) {
		final UserProfile profile = connection.fetchUserProfile();

		final User user = userRepository.findByEmail(profile.getEmail());

		// Pass back the email to associate the connection with in
		// case of existing user
		if (null != user) {
			return profile.getEmail();
		}

		// With passing null back we do not allow non-existing users to sing in
		// with the provider id
		return null;
	}

}
