package exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dto.ErrorMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOG = LogManager.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorMessage unexpected(Exception e) {
		LOG.error("Unexpected exception {}", e.getMessage());
		return new ErrorMessage(e.getMessage());
	}

	@ExceptionHandler(value = { DeleteException.class, UpdateException.class })
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody ErrorMessage entityExistingProblem(Exception e) {
		LOG.warn("Unprocesable entity {}", e.getMessage());
		return new ErrorMessage(e.getMessage());
	}
}
