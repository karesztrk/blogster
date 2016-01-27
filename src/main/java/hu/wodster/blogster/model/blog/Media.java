package hu.wodster.blogster.model.blog;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.validator.constraints.URL;

@Embeddable
public class Media implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4331149926173758050L;

	@URL
	private String url;

	@Enumerated(EnumType.STRING)
	private MediaType type;

	public Media() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public MediaType getType() {
		return type;
	}

	public void setType(final MediaType type) {
		this.type = type;
	}

}
