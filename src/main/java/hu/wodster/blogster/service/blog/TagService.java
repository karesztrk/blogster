package hu.wodster.blogster.service.blog;

import hu.wodster.blogster.model.blog.Post;
import hu.wodster.blogster.model.blog.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

	/**
	 * Saves or updates a tag.
	 *
	 * @param tag
	 *            a tag
	 * @return the persisted tag instance
	 */
	public Tag save(final Tag tag);

	public Page<Post> findPostByTitle(final String title,
			final Pageable pageable);
}
