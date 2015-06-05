package hu.sample.blogster.service.exception;

import hu.sample.blogster.common.exception.CustomNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GeneralExceptionHandler {

	private final Logger LOGGER = LoggerFactory
			.getLogger(GeneralExceptionHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CustomNotFoundException.class)
	@ResponseBody
	public String handleNotFoundException(final Exception exception) {
		logException(exception);
		return "{\"message\":\"Customer not found\"}";
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public String handleBadRequestException(final Exception exception) {
		logException(exception);
		return "{\"message\":\"" + exception.getMessage() + "\"}";
	}

	private void logException(final Exception exception) {
		LOGGER.error("Handler caught exception", exception);
	}
}
