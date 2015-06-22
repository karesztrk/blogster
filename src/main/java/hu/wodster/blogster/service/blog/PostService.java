package hu.wodster.blogster.service.blog;

import hu.wodster.blogster.common.core.UserAccount;
import hu.wodster.blogster.model.blog.Post;

import org.springframework.data.domain.Page;

/**
 * Business interface of {@link Post} management.
 *
 * @author Károly
 *
 */
public interface PostService {

	/**
	 * Saves one or more posts for demo purposes.
	 */
	public void saveDemoPost();

	/**
	 * Returns with a pageable list of posts.
	 *
	 * @param page
	 *            the requested page
	 * @return post page
	 */
	public Page<Post> list(final Integer page);

	/**
	 * Returns with a pageable list of posts which match to the required tag.
	 *
	 * @param tagName
	 *            required tag
	 * @param page
	 *            the requested page
	 * @return post page
	 */
	public Page<Post> listByTag(final String tagName, final Integer page);

	/**
	 * Saves or updates a post.
	 *
	 * @param user
	 *            the author
	 * @param post
	 *            the post
	 * @return the persisted post instance
	 */
	public Post save(final UserAccount user, final Post post);

	/**
	 * Finds a post by its identfier.
	 *
	 * @param id
	 *            identifier
	 * @return the existing post instance
	 */
	public Post find(final Long id);

	/**
	 * Finds a post by its public identifier.
	 *
	 * @param publicId
	 *            identifier
	 * @return the existing post instance
	 */
	public Post find(final String publicId);

}
