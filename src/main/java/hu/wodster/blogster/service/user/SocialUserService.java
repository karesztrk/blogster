package hu.wodster.blogster.service.user;

import hu.wodster.blogster.common.core.SocialUserAccount;
import hu.wodster.blogster.model.social.SocialConnection;
import hu.wodster.blogster.model.user.User;
import hu.wodster.blogster.repository.social.SocialConnectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Social extension for the built-in user service to find application users.
 *
 * @author Károly
 */
@Service
public class SocialUserService extends AbstractUserService implements
		SocialUserDetailsService {

	/**
	 * Social user repository.
	 */
	@Autowired
	private SocialConnectionRepository socialConnectionRepository;

	@Override
	public SocialUserDetails loadUserByUserId(final String userId)
			throws UsernameNotFoundException, DataAccessException {

		final SocialConnection connection = socialConnectionRepository
				.findTopByProviderUserIdOrderByRankAsc(userId);
		if (null == connection) {
			throw new UsernameNotFoundException(
					"The user with the given social id not found");
		}

		final User user = connection.getUser();

		return new SocialUserAccount(userId, user.getEmail(),
				user.getPassword(), getAuthority(user));
	}
}
