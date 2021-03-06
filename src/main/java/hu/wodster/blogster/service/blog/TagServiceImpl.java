package hu.wodster.blogster.service.blog;

import hu.wodster.blogster.model.blog.Tag;
import hu.wodster.blogster.repository.blog.TagRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

	@Override
	public List<Tag> findMostPopularTags() {
		return repository.findMostPopularTags(new PageRequest(0, 10));
	}

	@Override
	public Tag save(final Tag tag) {
		return repository.save(tag);
	}

}
