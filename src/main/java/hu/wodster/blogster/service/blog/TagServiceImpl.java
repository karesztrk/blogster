package hu.wodster.blogster.service.blog;

import hu.wodster.blogster.model.blog.Tag;
import hu.wodster.blogster.repository.blog.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business service implementation of the {@link Tag} management.
 *
 * @author Károly
 *
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

	/**
	 * Tag repository.
	 */
	@Autowired
	private TagRepository repository;

	@Override
	public Tag findByTitle(final String title) {
		return repository.findByTitle(title);
	}

}
