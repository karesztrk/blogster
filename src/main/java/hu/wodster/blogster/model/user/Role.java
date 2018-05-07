package hu.wodster.blogster.model.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignable roles to a user.
 *
 * @author Károly
 *
 */
public enum Role {

	/** Administrator user. */
	ADMINISTRATOR(1),

	/** Normal user. */
	USER(2);

	/** Order of the role. The lower is the better. */
	private int precedence;

	/**
	 *
	 * @param precedence
	 */
	private Role(final int precedence) {
		this.precedence = precedence;
	}

	/**
	 *
	 * @return
	 */
	public int getPrecedence() {
		return precedence;
	}

	/**
	 * Collects all sub roles (which is included by a specific role) for a type.
	 *
	 * @param role
	 * @return
	 */
	public List<Role> getSubRoles() {
		final List<Role> subRoles = new ArrayList<Role>();
		for (final Role r : values()) {
			if (r.getPrecedence() <= this.getPrecedence() && !r.equals(this)) {
				subRoles.add(r);
			}
		}
		return subRoles;
	}

}
