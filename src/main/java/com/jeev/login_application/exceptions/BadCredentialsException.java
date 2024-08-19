package com.jeev.login_application.exceptions;

public class BadCredentialsException extends RuntimeException {
	
	public BadCredentialsException(String message) {
		super(message);
	}
}
