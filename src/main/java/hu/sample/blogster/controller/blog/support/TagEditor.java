package hu.sample.blogster.controller.blog.support;

import hu.sample.blogster.model.blog.Tag;
import hu.sample.blogster.service.blog.TagService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Component;

/**
 * The service component coordinates how should be the {@link Tag} instances
 * translated to text and back.
 *
 * @author Károly
 *
 */
@Component
public class TagEditor extends CustomCollectionEditor {

	/**
	 * Tag business service.
	 */
	@Autowired
	private TagService service;

	/**
	 * Builds a default tag editor instance.
	 */
	public TagEditor() {
		super(Set.class);
	}

	@Override
	protected Object convertElement(final Object element) {

		if (element instanceof Tag) {
			return element;
		}

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

	@Override
	@SuppressWarnings("unchecked")
	public String getAsText() {
		final StringBuilder textBuilder = new StringBuilder();
		final Object element = getValue();

		// Means no appropriate text representation
		if (null == element) {
			return null;
		}

		// Only bother with collections/iterables
		if (element instanceof Iterable<?>) {

			final Iterable<Object> iterableElement = (Iterable<Object>) element;
			for (final Object entry : iterableElement) {

				// Append separator
				if (textBuilder.length() > 0) {
					textBuilder.append(",");
				}

				// Append text value
				if (entry instanceof Tag) {
					textBuilder.append(((Tag) entry).getTitle());
				} else {
					textBuilder.append(entry.toString());
				}

			}
		}

		return textBuilder.toString();
	}
}
