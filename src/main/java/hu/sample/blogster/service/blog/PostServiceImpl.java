package hu.sample.blogster.service.blog;

import hu.sample.blogster.common.exception.CustomNotFoundException;
import hu.sample.blogster.model.blog.Post;
import hu.sample.blogster.repository.blog.PostRepository;
import hu.sample.blogster.repository.user.UserRepository;
import hu.sample.blogster.service.user.UserService;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public void saveDemoPost() {
		long posts = postRepository.count();

		if (posts == 0) {
			postRepository.save(createDemoPost());
		}
	}

	private Post createDemoPost() {
		Post demo = new Post();
		demo.setDate(Calendar.getInstance().getTime());
		demo.setTitle("Sample training post");
		demo.setContent("Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "
				+ "Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. "
				+ "Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.");
		demo.setUser(userService.findMasterUser());
		return demo;
	}

	@Override
	public Page<Post> list(Integer page) {
		PageRequest request = new PageRequest(page - 1, PAGE_SIZE,
				Sort.Direction.DESC, "date");
		return postRepository.findAll(request);
	}

	@Override
	public Post save(User user, Post post) {

		hu.sample.blogster.model.user.User currentUser = userRepository
				.findByEmail(user.getUsername());
		if (null == currentUser) {
			throw new CustomNotFoundException();
		}

		post.setUser(currentUser);
		if (null == post.getDate()) {
			post.setDate(Calendar.getInstance().getTime());
		}

		return postRepository.save(post);
	}
}
