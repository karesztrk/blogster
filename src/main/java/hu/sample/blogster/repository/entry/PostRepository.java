package hu.sample.blogster.repository.entry;

import hu.sample.blogster.entity.entry.Post;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long>{

}
