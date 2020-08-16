package org.crud.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.crud.api.response.AppResponse;
import org.crud.api.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<AppResponse> handleUserIdNotFoundException(HttpServletRequest request, Exception ex) {
		return ResponseEntity.ok(new AppResponse(HttpStatus.NOT_FOUND, 
				ex.getMessage(), 
				new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex.toString())));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<AppResponse> handleUserNotFoundException(HttpServletRequest request, Exception ex) {
		return ResponseEntity.ok(new AppResponse(HttpStatus.NOT_FOUND, 
				ex.getMessage(), 
				new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex.toString())));
	}
	
	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<AppResponse> handleUserDisabledException(HttpServletRequest request, Exception ex) {
		return ResponseEntity.ok(new AppResponse(HttpStatus.BAD_REQUEST, 
				ex.getMessage(), 
				new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.toString())));
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<AppResponse> handleBadUserCredentialsException(HttpServletRequest request, Exception ex) {
		return ResponseEntity.ok(new AppResponse(HttpStatus.BAD_REQUEST, 
				ex.getMessage(), 
				new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.toString())));
	}
	
	
}
