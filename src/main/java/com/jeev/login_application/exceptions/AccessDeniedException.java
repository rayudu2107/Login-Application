package com.jeev.login_application.exceptions;

public class AccessDeniedException extends RuntimeException {
	
	public AccessDeniedException(String message) {
		super(message);
	}
}