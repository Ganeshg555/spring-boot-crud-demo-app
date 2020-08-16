package org.crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UserIdNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8870110581143769062L;
	
	public UserIdNotFoundException(String message) {
		super(message);
	}

}
