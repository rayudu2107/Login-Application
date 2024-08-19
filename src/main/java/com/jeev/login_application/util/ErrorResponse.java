package com.jeev.login_application.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

	private final HttpStatus status;
	private final String message;
	private final String details;

}
