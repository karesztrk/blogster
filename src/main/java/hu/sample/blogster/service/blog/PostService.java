package hu.sample.blogster.service.blog;

import hu.sample.blogster.entity.blog.Post;

import org.springframework.data.domain.Page;

public interface PostService {

	public void saveDemoPost();

	public Page<Post> list(Integer page);

	public Post save(Post post);

}
