package hu.sample.blogster.service.blog;

import hu.sample.blogster.model.blog.Tag;

/**
 * Business interface of {@link Tag} management.
 *
 * @author Károly
 *
 */
public interface TagService {

	/**
	 * Finds a single tag by its title.
	 *
	 * @param title
	 *            title to be used
	 * @return the tag instance or null
	 */
	public Tag findByTitle(final String title);
}
