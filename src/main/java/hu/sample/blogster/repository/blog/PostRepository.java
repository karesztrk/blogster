package hu.sample.blogster.repository.blog;

import hu.sample.blogster.model.blog.Post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

	@EntityGraph(value = "Post.tags", type = EntityGraphType.LOAD)
	public Post findByPublicId(final String publicId);

}
