package hu.sample.blogster.service.blog;

import hu.sample.blogster.model.blog.Tag;

public interface TagService {

	public Tag findByTitle(final String title);
}
