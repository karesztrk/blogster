package hu.wodster.blogster.service.blog;

import hu.wodster.blogster.common.core.UserAccount;
import hu.wodster.blogster.common.exception.CustomNotFoundException;
import hu.wodster.blogster.common.exception.InvalidPostPublicId;
import hu.wodster.blogster.model.blog.Post;
import hu.wodster.blogster.model.blog.Tag;
import hu.wodster.blogster.model.user.User;
import hu.wodster.blogster.repository.blog.PostRepository;
import hu.wodster.blogster.repository.user.UserRepository;
import hu.wodster.blogster.service.user.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Business service implementation of the {@link Post} management.
 *
 * @author Károly
 *
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

	/**
	 * Maximum page size to be displayed. Later the client should be to define
	 * this.
	 */
	private static final int PAGE_SIZE = 10;

	/**
	 * User manager service.
	 */
	@Autowired
	private UserService userService;

	/**
	 * Post repository.
	 */
	@Autowired
	private PostRepository postRepository;

	/**
	 * User repository.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Tag service.
	 */
	@Autowired
	private TagService tagService;

	@Override
	public void saveDemoPost() {
		final long posts = postRepository.count();

		if (posts == 0) {
			final Post post = createDemoPost(userService.findMasterUser());
			final UserAccount user = new UserAccount(post.getUser().getEmail(),
					null);
			save(user, post);
		}
	}

	/**
	 * Builds a demo post instance.
	 *
	 * @param user
	 *            author of the post
	 * @return post instance
	 */
	private static Post createDemoPost(final User user) {
		final Post demo = new Post();
		demo.setDate(Calendar.getInstance().getTime());
		demo.setTitle("wodster training post");
		demo.setContent("Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "
				+ "Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. "
				+ "Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.");
		demo.setUser(user);
		demo.setPublicId(generatePublicId(demo));
		return demo;
	}

	@Override
	public Page<Post> list(final Integer page) {
		final PageRequest request = new PageRequest(page - 1, PAGE_SIZE,
				Sort.Direction.DESC, "date");
		return postRepository.findAll(request);
	}

	@Override
	public Page<Post> listByTag(final String tagName, final Integer page) {
		final PageRequest request = new PageRequest(page - 1, PAGE_SIZE);
		return tagService.findPostByTitle(tagName, request);
	}

	@Override
	public Post save(final UserAccount user, final Post post) {

		final hu.wodster.blogster.model.user.User currentUser = userRepository
				.findByEmail(user.getUsername());
		if (null == currentUser) {
			throw new CustomNotFoundException();
		}

		post.setUser(currentUser);
		if (null == post.getDate()) {
			post.setDate(Calendar.getInstance().getTime());
		}

		if (StringUtils.isEmpty(post.getTitle())) {
			post.setTitle(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		}

		if (StringUtils.isEmpty(post.getPublicId())) {
			post.setPublicId(generatePublicId(post));
		}

		// Save the attached tags
		for (final Tag tag : post.getTags()) {
			tagService.save(tag);
		}

		return postRepository.save(post);
	}

	@Override
	public Post find(final Long id) {
		if (null == id || 0 == id) {
			throw new IllegalArgumentException("Post identifier is mandatory");
		}

		return postRepository.findOne(id);
	}

	@Override
	public Post find(final String publicId) {
		if (StringUtils.isEmpty(publicId)) {
			throw new IllegalArgumentException("Post publicId is mandatory");
		}

		if (!isPublicIdValid(publicId)) {
			throw new InvalidPostPublicId();
		}

		return postRepository.findByPublicId(publicId);
	}

	/**
	 * Generated a unique public identifier for a post instance.
	 *
	 * @param post
	 *            a post to be used
	 * @return generated identifier
	 */
	private static String generatePublicId(final Post post) {

		String title = post.getTitle().toLowerCase();
		title = title.replaceAll("\\s", "-");
		title = title.substring(0,
				Math.min(title.length(), Post.POST_MAX_PUBLICID_LENGTH));
		try {
			return URLEncoder.encode(title, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new InvalidPostPublicId(e);
		}

	}

	/**
	 * Decides whether the given post public identifier valid.
	 *
	 * @param publicId
	 *            id to be checked
	 * @return true if the id is valid
	 */
	private static boolean isPublicIdValid(final String publicId) {
		return Post.POST_MIN_PUBLICID_LENGTH <= publicId.length()
				&& publicId.length() <= Post.POST_MAX_PUBLICID_LENGTH;
	}

}
