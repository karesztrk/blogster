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

	public Page<Post> list(final Integer page);

	public Page<Post> listByTag(final String tagName, final Integer page);

	public Post save(final UserAccount user, final Post post);

	public Post find(final Long id);

	public Post find(final String publicId);

}
