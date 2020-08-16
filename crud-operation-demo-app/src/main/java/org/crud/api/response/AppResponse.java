package org.crud.api.response;

import org.springframework.http.HttpStatus;

public class AppResponse {
	private HttpStatus status;
	private String message;
	private Object responseData;
	
	public AppResponse(HttpStatus status, String message, Object responseData) {
		super();
		this.status = status;
		this.message = message;
		this.responseData = responseData;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
}
