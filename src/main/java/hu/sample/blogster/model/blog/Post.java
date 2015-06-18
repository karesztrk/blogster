package hu.sample.blogster.model.blog;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "post")
@NamedEntityGraph(name = "Post.tags", attributeNodes = @NamedAttributeNode("tags"))
public class Post extends Entry {

	public static final int POST_MIN_PUBLICID_LENGTH = 3;

	public static final int POST_MAX_PUBLICID_LENGTH = 20;

	/**
	 *
	 */
	private static final long serialVersionUID = 4185940812055856077L;

	private String title;

	@Length(min = POST_MIN_PUBLICID_LENGTH, max = POST_MAX_PUBLICID_LENGTH)
	@NaturalId
	@NotNull
	private String publicId;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "post_tag", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	private Collection<Tag> tags;

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(final String publicId) {
		this.publicId = publicId;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public void setTags(final Collection<Tag> tags) {
		this.tags = tags;
	}

}
