package com.jeev.login_application.exceptions;

public class UserNotFoundByIdException extends RuntimeException {

	public UserNotFoundByIdException(String message) {
		super(message);
	}
}
