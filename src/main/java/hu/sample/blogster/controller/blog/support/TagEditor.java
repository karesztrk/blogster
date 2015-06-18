package hu.sample.blogster.controller.blog.support;

import hu.sample.blogster.model.blog.Tag;
import hu.sample.blogster.service.blog.TagService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Component;

@Component
public class TagEditor extends CustomCollectionEditor {

	@Autowired
	private TagService service;

	public TagEditor() {
		super(Set.class);
	}

	@Override
	protected Object convertElement(final Object element) {
		final Tag tag = service.findByTitle((String) element);
		if (null != tag) {
			return tag;
		}

		final Tag newTag = new Tag();
		newTag.setTitle((String) element);
		return newTag;
	}

	@Override
	public void setAsText(final String text) throws IllegalArgumentException {
		final String[] tags = text.split(",");
		setValue(tags);
	}

}
