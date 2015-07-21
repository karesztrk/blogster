package hu.wodster.blogster.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Contains all information which is mandatory to communicate with a social
 * network behalf of the user.
 *
 * @author Károly
 *
 */
@Entity
@Table(name = "user_network")
public class SocialNetworkProfile implements Serializable {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -3224040565034191957L;

	/**
	 * Owner of the profile.
	 */
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 * Type of the network.
	 */
	@Id
	@Enumerated(EnumType.STRING)
	private SocialNetwork type;

	/**
	 * OAuth access token.
	 */
	@NotNull
	@Column(name = "access_token")
	private String accessToken;

	/**
	 *
	 * @return
	 */
	public SocialNetwork getType() {
		return type;
	}

	/**
	 *
	 * @param network
	 */
	public void setType(final SocialNetwork network) {
		this.type = network;
	}

	/**
	 *
	 * @return
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 *
	 * @param accessToken
	 */
	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

}
