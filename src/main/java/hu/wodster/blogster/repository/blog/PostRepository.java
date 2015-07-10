package hu.wodster.blogster.repository.blog;

import hu.wodster.blogster.model.blog.Post;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository to manage {@link Post} entities.
 *
 * @author Károly
 *
 */
public interface PostRepository extends JpaRepository<Post, Long> {

	/**
	 * Finds a post by its public identifier. It also fetches the attached tags
	 * by default.
	 *
	 * @param publicId
	 *            id to be used
	 * @return the post or null
	 */
	@EntityGraph(value = "Post.tags", type = EntityGraphType.LOAD)
	public Post findByPublicId(final String publicId);

	/**
	 * Tries to lookup the oldest post date in the database.
	 *
	 * @return
	 */
	@Query("select min(date) from Post post")
	public Date findOldestPostDate();

	/**
	 * Finds all posts between the given time interval.
	 *
	 * @param from
	 * @param to
	 * @return
	 */
	public Page<Post> findByDateBetween(final Date from, final Date to,
			final Pageable pageable);

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
	 * Searches the post by their content or title.
	 *
	 * @param title
	 *            title to find
	 * @param content
	 *            part of the content to find
	 * @param pageable
	 *            paging details: direction, sorting
	 * @return the matching posts
	 */
	public Page<Post> findByTitleContainingOrContentContaining(
			final String title, final String content, final Pageable pageable);
}
