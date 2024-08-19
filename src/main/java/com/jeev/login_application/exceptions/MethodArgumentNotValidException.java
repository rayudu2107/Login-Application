package com.jeev.login_application.exceptions;

public class MethodArgumentNotValidException extends RuntimeException {
	
	public MethodArgumentNotValidException(String message) {
		super(message);
	}
}
