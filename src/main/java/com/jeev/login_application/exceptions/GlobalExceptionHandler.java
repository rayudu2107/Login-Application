package com.jeev.login_application.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.jeev.login_application.util.ErrorResponse;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// Handle generic exceptions
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
		logger.error("Internal Server Error: {}", ex.getMessage(), ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
				ex.getMessage());
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@ExceptionHandler(UserAlreadyExistsByEmailException.class)
	protected ResponseEntity<ErrorResponse> handleDuplicateEmailException(UserAlreadyExistsByEmailException ex,
			WebRequest request) {
		logger.warn("User already exists: {}", ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "User Already Exists", ex.getMessage());
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<ErrorResponse> handleAuthenticationException(BadCredentialsException ex,
			WebRequest request) {
		logger.warn("Authentication failed: {}", ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED,
				"The given username or password is incorrect", ex.getMessage());
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		logger.warn("Access denied: {}", ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN, "Access Denied", ex.getMessage());
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@ExceptionHandler(UsersDataNotFoundException.class)
	protected ResponseEntity<ErrorResponse> handleUserNotFoundByException(UsersDataNotFoundException ex,
			WebRequest request) {
		logger.info("User not found: {}", ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "User Not Found", ex.getMessage());
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@ExceptionHandler(UserNotFoundByIdException.class)
	protected ResponseEntity<ErrorResponse> handleUserNotFoundByIdException(UserNotFoundByIdException ex,
			WebRequest request) {
		logger.info("User not found by ID: {}", ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "User Not Found by ID", ex.getMessage());
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		logger.warn("Validation failed: {}", errors);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation Failed", errors.toString());
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}
}
