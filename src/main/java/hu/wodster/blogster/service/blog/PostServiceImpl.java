package hu.wodster.blogster.service.blog;

import hu.wodster.blogster.common.core.UserAccount;
import hu.wodster.blogster.common.exception.CustomNotFoundException;
import hu.wodster.blogster.common.exception.InvalidPostPublicId;
import hu.wodster.blogster.model.blog.Archive;
import hu.wodster.blogster.model.blog.Post;
import hu.wodster.blogster.model.blog.Tag;
import hu.wodster.blogster.model.user.User;
import hu.wodster.blogster.repository.blog.PostRepository;
import hu.wodster.blogster.repository.user.UserRepository;
import hu.wodster.blogster.service.user.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
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
	private static final int PAGE_SIZE = 5;

	private static final String DEFAULT_TITLE_PATTERN = "yyyyMMdd";

	private static final String DEFAULT_PUBLICID_PATTERN = "yyyyMMdd_HHmm";

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
			final UserAccount user = new UserAccount(post.getUser().getEmail(), null);
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
		demo.setPublicId(generatePublicIdFromTitle(demo));
		return demo;
	}

	@Override
	public Page<Post> list(final Integer page) {
		final PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "date");
		return postRepository.findAll(request);
	}

	@Override
	public Page<Post> listByTag(final String tagName, final Integer page) {
		final PageRequest request = new PageRequest(page - 1, PAGE_SIZE);
		return postRepository.findPostByTitle(tagName, request);
	}

	@Override
	public Post save(final UserAccount user, final Post post) {

		final hu.wodster.blogster.model.user.User currentUser = userRepository.findByEmail(user.getUsername());
		if (null == currentUser) {
			throw new CustomNotFoundException();
		}

		post.setUser(currentUser);

		if (null == post.getDate()) {
			post.setDate(Calendar.getInstance().getTime());
		}

		final boolean generateTitle = StringUtils.isEmpty(post.getTitle()) ? true : false;

		if (generateTitle) {
			post.setTitle(new SimpleDateFormat(DEFAULT_TITLE_PATTERN).format(post.getDate()));

			post.setPublicId(generatePublicId(post));

		} else {

			post.setPublicId(generatePublicIdFromTitle(post));
		}

		// Save the attached tags
		if (null != post.getTags()) {
			for (final Tag tag : post.getTags()) {
				tagService.save(tag);
			}
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

	@Override
	public Page<Post> findInDateArchive(final Date date, final Integer page) {
		final PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "date");

		final Date from = DateUtils.truncate(date, Calendar.MONTH);
		final Date to = DateUtils.addMonths(from, 1);
		return postRepository.findByDateBetween(from, to, request);
	}

	@Override
	public List<Archive> getArchives() {
		final List<Archive> archives = new ArrayList<Archive>();

		final DateFormat archiveIdFormat = new SimpleDateFormat("yyyyMM");
		final Calendar now = Calendar.getInstance();
		final Calendar oldest = Calendar.getInstance();
		oldest.setTime(postRepository.findOldestPostDate());

		while (oldest.before(now)) {
			final Archive archive = new Archive();
			archive.setDate(oldest.getTime());
			archive.setName(archiveIdFormat.format(oldest.getTime()));
			archives.add(archive);
			oldest.add(Calendar.MONDAY, 1);
		}

		return archives;
	}

	@Override
	public Page<Post> findByContent(final String criteria, final Integer page) {
		final PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "date");
		// TODO exclude/filter HTML code from the content searching
		return postRepository.findByTitleContainingOrContentContaining(criteria, criteria, request);
	}

	/**
	 * Generated a unique public identifier for a post instance.
	 *
	 * @param post
	 *            a post to be used
	 * @return generated identifier
	 */
	private static String generatePublicId(final Post post) {
		final SimpleDateFormat df = new SimpleDateFormat(DEFAULT_PUBLICID_PATTERN);
		return df.format(post.getDate());
	}

	/**
	 * Generates a unique public identifier from a user defined title.
	 *
	 * @param post
	 * @return
	 */
	private static String generatePublicIdFromTitle(final Post post) {
		try {
			String title = post.getTitle().toLowerCase();
			title = title.replaceAll("\\s", "-");
			title = title.substring(0, Math.min(title.length(), Post.POST_MAX_PUBLICID_LENGTH));

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
		return Post.POST_MIN_PUBLICID_LENGTH <= publicId.length() && publicId.length() <= Post.POST_MAX_PUBLICID_LENGTH;
	}

}
