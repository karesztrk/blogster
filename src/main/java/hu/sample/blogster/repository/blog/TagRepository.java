package hu.sample.blogster.repository.blog;

import hu.sample.blogster.model.blog.Post;
import hu.sample.blogster.model.blog.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends JpaRepository<Tag, Long>,
		JpaSpecificationExecutor<Tag> {

	@Query(value = "select p from Tag tag inner join tag.posts p where tag.title = :title order by p.date desc", countQuery = "select count(p) from Tag tag inner join tag.posts p where tag.title = :title")
	public Page<Post> findPostByTitle(@Param("title") final String title,
			final Pageable pageable);

	public Tag findByTitle(final String title);
}
