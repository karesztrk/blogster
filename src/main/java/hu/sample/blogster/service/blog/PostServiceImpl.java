package hu.sample.blogster.service.blog;

import hu.sample.blogster.common.core.UserAccount;
import hu.sample.blogster.common.exception.CustomNotFoundException;
import hu.sample.blogster.common.exception.InvalidPostPublicId;
import hu.sample.blogster.model.blog.Post;
import hu.sample.blogster.repository.blog.PostRepository;
import hu.sample.blogster.repository.user.UserRepository;
import hu.sample.blogster.service.user.UserService;

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

@Service
@Transactional
public class PostServiceImpl implements PostService {

	private static final int PAGE_SIZE = 10;

	@Autowired
	private UserService userService;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveDemoPost() {
		final long posts = postRepository.count();

		if (posts == 0) {
			final Post post = createDemoPost();
			final UserAccount user = new UserAccount(post.getUser().getEmail(),
					null);
			save(user, post);
		}
	}

	private Post createDemoPost() {
		final Post demo = new Post();
		demo.setDate(Calendar.getInstance().getTime());
		demo.setTitle("Sample training post");
		demo.setContent("Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "
				+ "Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. "
				+ "Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.");
		demo.setUser(userService.findMasterUser());
		return demo;
	}

	@Override
	public Page<Post> list(final Integer page) {
		final PageRequest request = new PageRequest(page - 1, PAGE_SIZE,
				Sort.Direction.DESC, "date");
		return postRepository.findAll(request);
	}

	@Override
	public Post save(final UserAccount user, final Post post) {

		final hu.sample.blogster.model.user.User currentUser = userRepository
				.findByEmail(user.getUsername());
		if (null == currentUser) {
			throw new CustomNotFoundException();
		}

		post.setUser(currentUser);
		if (null == post.getDate()) {
			post.setDate(Calendar.getInstance().getTime());
		}

		if (null == post.getTitle()) {
			post.setTitle(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		}

		if (StringUtils.isEmpty(post.getPublicId())) {
			post.setPublicId(generatePublicId(post));
		}

		// TODO check publicID exists already

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

	private static String generatePublicId(final Post post) {
		// TODO umlauts?
		final String title = post.getTitle();
		return title.replaceAll("\\s", "-")
				.substring(0, Post.POST_MAX_PUBLICID_LENGTH).toLowerCase();
	}

	private static boolean isPublicIdValid(final String publicId) {
		return Post.POST_MIN_PUBLICID_LENGTH <= publicId.length()
				&& publicId.length() <= Post.POST_MAX_PUBLICID_LENGTH;
	}

}
