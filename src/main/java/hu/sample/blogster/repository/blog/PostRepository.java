package hu.sample.blogster.repository.blog;

import hu.sample.blogster.model.blog.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
