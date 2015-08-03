package hu.wodster.blogster.service.social;

import hu.wodster.blogster.service.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Application specific SSO adapter.
 *
 * @author Károly
 */
@Service
public class SignInAdapter implements
		org.springframework.social.connect.web.SignInAdapter {

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SignInAdapter.class);

	/**
	 * Social user details service.
	 */
	@Autowired
	private SocialUserDetailsService socialUserDetailsService;

	/**
	 * User service to authenticate
	 */
	@Autowired
	private UserService userService;

	@Override
	public String signIn(final String userId, final Connection<?> connection,
			final NativeWebRequest request) {

		UserDetails user = findSocialUser(userId);

		// try to look up the user by social profile held in the connection
		if (null == user) {
			final UserProfile profile = connection.fetchUserProfile();
			user = findLocalUser(profile.getEmail());
		}

		if (null != user) {

			SecurityContextHolder.getContext().setAuthentication(
					new SocialAuthenticationToken(connection, user, null, user
							.getAuthorities()));
		}

		// If defined holds the navigation to redirect the user to
		return null;
	}

	/**
	 * Looks up the user detail from the social user id.
	 *
	 * @param userId
	 * @return the user details or null if not found
	 */
	private SocialUserDetails findSocialUser(final String userId) {

		try {
			return socialUserDetailsService.loadUserByUserId(userId);
		} catch (final UsernameNotFoundException e) {
			LOGGER.warn("Social user with id {0} not found", userId);
		}

		return null;
	}

	/**
	 * Looks up the user details from a local username.
	 *
	 * @param username
	 * @return the user details or null if not found
	 */
	private UserDetails findLocalUser(final String username) {
		try {
			return userService.loadUserByUsername(username);
		} catch (final UsernameNotFoundException e) {
			LOGGER.warn("Local user with username {0} not found", username);
		}

		return null;
	}

}
