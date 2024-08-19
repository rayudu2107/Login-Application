package com.jeev.login_application.exceptions;

public class UserAlreadyExistsByEmailException extends RuntimeException {

	public UserAlreadyExistsByEmailException(String message) {
		super(message);
	}
}
