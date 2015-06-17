package hu.sample.blogster.model.blog;

import hu.sample.blogster.model.user.User;

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

@MappedSuperclass
public abstract class Entry implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7663592937770531066L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@org.hibernate.annotations.Type(type = "org.hibernate.type.StringClobType")
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@ManyToOne
	@NotNull
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
