package hu.sample.blogster.entity.entry;

import javax.persistence.Entity;

@Entity
public class Post extends Entry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4185940812055856077L;

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
