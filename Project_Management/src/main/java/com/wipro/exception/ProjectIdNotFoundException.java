package com.wipro.exception;

public class ProjectIdNotFoundException extends Exception {
	private String message;

	public ProjectIdNotFoundException(String message) {
		super(message);
		
	}
	

}
