package hu.sample.blogster.common.exception;

/**
 * Thrown if a post with a specific identifier cannot be found in the
 * application.
 *
 * @author Károly
 *
 */
public class InvalidPostPublicId extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 5486119461448405172L;

	/**
	 *
	 */
	public InvalidPostPublicId() {
		super();
	}

	/**
	 *
	 * @param message
	 * @param cause
	 */
	public InvalidPostPublicId(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 *
	 * @param message
	 */
	public InvalidPostPublicId(final String message) {
		super(message);
	}

	/**
	 *
	 * @param cause
	 */
	public InvalidPostPublicId(final Throwable cause) {
		super(cause);
	}

}
