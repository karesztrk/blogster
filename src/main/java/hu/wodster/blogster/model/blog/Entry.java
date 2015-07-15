package hu.wodster.blogster.model.blog;

import hu.wodster.blogster.model.user.User;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * This is a basic representation of a representable blog element. It can be a
 * post, a journal entry or a remainder, etc.
 *
 * @author Károly
 *
 */
@MappedSuperclass
public abstract class Entry implements Serializable {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 7663592937770531066L;

	/**
	 * Entry identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	/**
	 * Written entry content.
	 */
	@org.hibernate.annotations.Type(type = "org.hibernate.type.StringClobType")
	private String content;

	/**
	 * Date of publication.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date date;

	/**
	 * The author of the entry.
	 */
	@ManyToOne
	@NotNull
	private User user;

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
	public String getContent() {
		return content;
	}

	/**
	 *
	 * @param content
	 */
	public void setContent(final String content) {
		this.content = content;
	}

	/**
	 *
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 *
	 * @param date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 *
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 *
	 * @param user
	 */
	public void setUser(final User user) {
		this.user = user;
	}

}
