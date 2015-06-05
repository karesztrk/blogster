package hu.sample.blogster.service.blog;

import hu.sample.blogster.model.blog.Post;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;

public interface PostService {

	public void saveDemoPost();

	public Page<Post> list(Integer page);

	public Post save(User user, Post post);

}
