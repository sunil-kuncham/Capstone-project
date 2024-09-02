package com.wipro.exception;

public class UserIdNotFoundException extends Exception {

	private String message;

	public UserIdNotFoundException(String message) {
		super();
		this.message = message;
	}

}
