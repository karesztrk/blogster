package hu.wodster.blogster.repository.blog;

import hu.wodster.blogster.model.blog.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface to manage {@link Tag} entities.
 *
 * @author Károly
 *
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

	/**
	 * Finds a tag by its title.
	 *
	 * @param title
	 *            the title to be looked for
	 * @return the found tag or null
	 */
	public Tag findByTitle(final String title);
}
