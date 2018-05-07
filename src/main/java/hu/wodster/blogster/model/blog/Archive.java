package hu.wodster.blogster.model.blog;

import java.io.Serializable;
import java.util.Date;

public class Archive implements Serializable {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -7959030149698146395L;

	private String name;

	private Date date;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

}
