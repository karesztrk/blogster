package hu.sample.blogster.service.exception;

import hu.sample.blogster.common.exception.CustomNotFoundException;
import hu.sample.blogster.common.exception.InvalidPostPublicId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The service translates the application specific business errors into HTTP
 * responses (code or body).
 *
 * @author Károly
 *
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GeneralExceptionHandler {

	/**
	 * Logger.
	 */
	private final Logger LOGGER = LoggerFactory
			.getLogger(GeneralExceptionHandler.class);

	/**
	 * HTTP 404 Not found.
	 *
	 * @param exception
	 *            occurred error
	 * @return HTTP response body
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CustomNotFoundException.class)
	@ResponseBody
	public String handleNotFoundException(final Exception exception) {
		logException(exception);
		return "{\"message\":\"Customer not found\"}";
	}

	/**
	 * HTTP 400 Bad request for generic errors.
	 *
	 * @param exception
	 *            occurred error
	 * @return HTTP response body
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public String handleBadRequestException(final Exception exception) {
		logException(exception);
		return "{\"message\":\"" + exception.getMessage() + "\"}";
	}

	/**
	 * HTTP 400 Bad request for posts.
	 *
	 * @param exception
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidPostPublicId.class)
	@ResponseBody
	public String handleInvalidPostPublicIdException(final Exception exception) {
		logException(exception);
		return "{\"message\":\"Unknown post\"}";
	}

	/**
	 * Simple logging of the error.
	 *
	 * @param exception
	 *            to be logged
	 */
	private void logException(final Exception exception) {
		LOGGER.error("Handler caught exception", exception);
	}
}
