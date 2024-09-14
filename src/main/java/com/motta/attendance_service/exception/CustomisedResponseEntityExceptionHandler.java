package com.motta.attendance_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AttendanceNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleAttendanceNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidAttendanceException.class)
	public final ResponseEntity<ErrorDetails> handleInvalidAttendanceException(Exception ex, WebRequest request)
			throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				"Total Erros = " + ex.getErrorCount() + " Error message = " + ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));

		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleResourceNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}

}
