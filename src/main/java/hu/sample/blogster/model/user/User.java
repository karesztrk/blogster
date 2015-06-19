package hu.sample.blogster.model.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The consumer of the application. The user can be log in and work with the
 * application.
 *
 * @author Károly
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -2700940300128536024L;

	/**
	 * User identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	/**
	 * Email address of the user.
	 */
	private String email;

	/**
	 * Password of the user to authenticate with.
	 */
	private String password;

	/**
	 * Displayable name of the user.
	 */
	private String name;

	/**
	 * Attached role.
	 */
	@Enumerated(EnumType.STRING)
	private Role role;

	/**
	 *
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 *
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 *
	 * @param email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 *
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * @param password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 *
	 * @return
	 */
	public Role getRole() {
		return role;
	}

	/**
	 *
	 * @param role
	 */
	public void setRole(final Role role) {
		this.role = role;
	}

}
