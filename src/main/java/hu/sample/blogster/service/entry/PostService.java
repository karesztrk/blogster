package hu.sample.blogster.service.entry;

import hu.sample.blogster.entity.entry.Post;

import org.springframework.data.domain.Page;

public interface PostService {

	public void saveDemoPost();

	public Page<Post> list(Integer page);
}
