package hu.sample.blogster.service.blog;

import hu.sample.blogster.model.blog.Tag;
import hu.sample.blogster.repository.blog.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository repository;

	@Override
	public Tag findByTitle(final String title) {
		return repository.findByTitle(title);
	}

}
