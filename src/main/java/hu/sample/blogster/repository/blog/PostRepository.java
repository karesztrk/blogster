package hu.sample.blogster.repository.blog;

import hu.sample.blogster.entity.blog.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
