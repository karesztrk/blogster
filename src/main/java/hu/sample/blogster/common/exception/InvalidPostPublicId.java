package hu.sample.blogster.common.exception;

public class InvalidPostPublicId extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 5486119461448405172L;

	public InvalidPostPublicId() {
		super();
	}

	public InvalidPostPublicId(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidPostPublicId(final String message) {
		super(message);
	}

	public InvalidPostPublicId(final Throwable cause) {
		super(cause);
	}

}
