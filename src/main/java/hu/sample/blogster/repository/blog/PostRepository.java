package hu.sample.blogster.repository.blog;

import hu.sample.blogster.model.blog.Post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
