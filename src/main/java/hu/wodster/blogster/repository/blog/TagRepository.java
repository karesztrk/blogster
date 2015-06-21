package hu.wodster.blogster.repository.blog;

import hu.wodster.blogster.model.blog.Post;
import hu.wodster.blogster.model.blog.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface to manage {@link Tag} entities.
 *
 * @author Károly
 *
 */
public interface TagRepository extends JpaRepository<Tag, Long>,
		JpaSpecificationExecutor<Tag> {

	/**
	 * Returns with a pagable collection of posts which has the given tag title.
	 *
	 * @param title
	 *            title to be looked for
	 * @param pageable
	 *            paging details: direction, sorting
	 * @return the matching posts
	 */
	@Query(value = "select p from Tag tag inner join tag.posts p where tag.title = :title order by p.date desc", countQuery = "select count(p) from Tag tag inner join tag.posts p where tag.title = :title")
	public Page<Post> findPostByTitle(@Param("title") final String title,
			final Pageable pageable);

	/**
	 * Finds a tag by its title.
	 *
	 * @param title
	 *            the title to be looked for
	 * @return the found tag or null
	 */
	public Tag findByTitle(final String title);
}
