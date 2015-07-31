package hu.wodster.blogster.common.core;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

/**
 * Custom social user details implementation wired into the social security
 * module.
 *
 * @author Károly
 *
 */
public class SocialUserAccount extends UserAccount implements SocialUserDetails {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -8943414650668220848L;

	/**
	 * User id on the provider side.
	 */
	private final String userId;

	/**
	 * Creates a new user account.
	 *
	 * @param userId
	 * @param email
	 * @param password
	 * @param authorities
	 */
	public SocialUserAccount(final String userId, final String email,
			final String password, final GrantedAuthority[] authorities) {
		super(email, password, authorities);
		this.userId = userId;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SocialUserAccount other = (SocialUserAccount) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
