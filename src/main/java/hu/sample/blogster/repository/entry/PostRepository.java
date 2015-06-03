package hu.sample.blogster.repository.entry;

import hu.sample.blogster.entity.entry.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
