package hu.wodster.blogster.model.blog;

import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

/**
 * A single blog post entry. A post has a public id which serves as a natural
 * identifer which can be also displayed to the client.
 *
 * @author Károly
 *
 */
@Entity
@Table(name = "post")
@NamedEntityGraphs(value = { @NamedEntityGraph(name = "Post.tags", attributeNodes = @NamedAttributeNode("tags")) })
public class Post extends Entry {

	/**
	 * Minimum length of the public id.
	 */
	public static final int POST_MIN_PUBLICID_LENGTH = 3;

	/**
	 * Maximum length of the public id.
	 */
	public static final int POST_MAX_PUBLICID_LENGTH = 20;

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 4185940812055856077L;

	/**
	 * Title of the post.
	 */
	private String title;

	/**
	 * Public identifier
	 */
	@Length(min = POST_MIN_PUBLICID_LENGTH, max = POST_MAX_PUBLICID_LENGTH)
	@NaturalId
	@NotNull
	private String publicId;

	/**
	 * Attached tags to this post. Deletion of the tag is not permitted from
	 * this side. Only allowing existing children to be cascaded on this side.
	 */
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "post_tag", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	private Collection<Tag> tags;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "url", column = @Column(name = "feature_media_url")),
			@AttributeOverride(name = "type", column = @Column(name = "feature_media_type")) })
	private Media media;

	/**
	 *
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *
	 * @param title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 *
	 * @return
	 */
	public String getPublicId() {
		return publicId;
	}

	/**
	 *
	 * @param publicId
	 */
	public void setPublicId(final String publicId) {
		this.publicId = publicId;
	}

	/**
	 *
	 * @return
	 */
	public Collection<Tag> getTags() {
		return tags;
	}

	/**
	 *
	 * @param tags
	 */
	public void setTags(final Collection<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the media
	 */
	public Media getMedia() {
		return media;
	}

	/**
	 * @param media
	 *            the media to set
	 */
	public void setMedia(final Media media) {
		this.media = media;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((publicId == null) ? 0 : publicId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;
		final Post other = (Post) obj;
		if (publicId == null) {
			if (other.publicId != null)
				return false;
		} else if (!publicId.equals(other.publicId))
			return false;
		return true;
	}

}
