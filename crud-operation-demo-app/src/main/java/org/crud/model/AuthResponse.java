package org.crud.model;

public class AuthResponse {
	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String jwt) {
		super();
		this.jwt = jwt;
	}
}
