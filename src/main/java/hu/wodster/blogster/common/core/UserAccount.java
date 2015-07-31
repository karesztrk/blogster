package hu.wodster.blogster.common.core;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Custom user details implementation wired into the core security module.
 *
 * @author Károly
 */
public class UserAccount implements UserDetails {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -742976988463393260L;

	/**
	 * User enabled.
	 */
	private final boolean enabled;

	/**
	 * User email.
	 */
	private final String email;

	/**
	 * User password
	 */
	private final String password;

	/**
	 * Granted roles.
	 */
	private final Collection<? extends GrantedAuthority> grantedAuthorities;

	/**
	 * Builds an empty user details.
	 */

	/**
	 * Builds user details with the most basic parameters.
	 *
	 * @param email
	 * @param password
	 * @param authorities
	 */
	public UserAccount(final String email, final String password,
			final GrantedAuthority... authorities) {
		this.enabled = true;
		this.email = email;
		this.password = password;
		this.grantedAuthorities = Arrays.asList(authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Gives back the user email.
	 *
	 * @return email address
	 */
	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserAccount other = (UserAccount) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
