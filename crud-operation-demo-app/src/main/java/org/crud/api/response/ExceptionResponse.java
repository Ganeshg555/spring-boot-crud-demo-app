package org.crud.api.response;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
	private HttpStatus status;
	private String message;
	private String exception;
	public ExceptionResponse(HttpStatus status, String message, String exception) {
		super();
		this.status = status;
		this.message = message;
		this.exception = exception;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public String getException() {
		return exception;
	}
	
	
}
