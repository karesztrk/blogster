package hu.sample.blogster.common.core;

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
	 *
	 */
	private static final long serialVersionUID = -742976988463393260L;

	private final boolean enabled;

	private final String email;

	private final String password;

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

	public String getEmail() {
		return email;
	}

}
