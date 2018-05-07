package hu.wodster.blogster.repository.blog;

import hu.wodster.blogster.model.blog.Tag;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

	/**
	 * Finds the first ten most popular tags.
	 *
	 * @param title
	 *            the title to be looked for
	 * @return the most popular tags
	 */
	@Query("select tag from Tag tag left join tag.posts group by tag.id order by count(tag.id) desc")
	public List<Tag> findMostPopularTags(final Pageable pageable);
}
