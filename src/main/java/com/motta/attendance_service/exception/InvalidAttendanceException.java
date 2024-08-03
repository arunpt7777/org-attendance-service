package com.motta.attendance_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidAttendanceException extends RuntimeException {

	public InvalidAttendanceException(String message) {
		super(message);
	}
}
