package hu.wodster.blogster.model.social;

import hu.wodster.blogster.model.user.User;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

/**
 * Represents a single connection for a user.
 *
 * @author Károly
 */
@Entity
@Table(name = "social_connection", uniqueConstraints = @UniqueConstraint(columnNames = {
		"network", "provider_user_id" }))
public class SocialConnection implements Serializable {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 5798276981655325543L;

	/**
	 * Primary key of connectino.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	/**
	 * Owner of the connection.
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 * Social network.
	 */
	@NotNull
	@Column(name = "network")
	@Enumerated(EnumType.STRING)
	private SocialNetwork network;

	/**
	 * Unique identifier of the user on the provider side.
	 */
	@Column(name = "provider_user_id")
	private String providerUserId;

	/**
	 * Display name of the user.
	 */
	@Column(name = "display_name")
	private String displayName;

	/**
	 * Profile URL of the user.
	 */
	@URL
	@Column(name = "profile_url")
	private String profileUrl;

	/**
	 * Photo image url.
	 */
	@URL
	@Column(name = "image_id")
	private String imageUrl;

	/**
	 * The token we can work with behalf of the user.
	 */
	@Column(name = "access_token")
	private String accessToken;

	/**
	 * User secret
	 */
	private String secret;

	/**
	 * The token to query a new access token.
	 */
	@Column(name = "refresh_token")
	private String refreshToken;

	/**
	 * Expiration time of the access token.
	 */
	@Column(name = "expire_time")
	private Long expireTime;

	/**
	 * Rank of the connection.
	 */
	private int rank;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public SocialNetwork getNetwork() {
		return network;
	}

	public void setNetwork(final SocialNetwork network) {
		this.network = network;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(final String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(final String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(final String secret) {
		this.secret = secret;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(final String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(final Long expireTime) {
		this.expireTime = expireTime;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(final int rank) {
		this.rank = rank;
	}

}
