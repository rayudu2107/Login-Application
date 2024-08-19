package com.jeev.login_application.service;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.jeev.login_application.dto.JwtAuthenticationResponse;
import com.jeev.login_application.dto.LoginRequest;
import com.jeev.login_application.dto.RegisterRequest;
import com.jeev.login_application.dto.UserResponse;

public interface IAuthenticationService {
	ResponseEntity<UserResponse> register(@Valid RegisterRequest registerRequest);

	ResponseEntity<JwtAuthenticationResponse> login(LoginRequest loginRequest);

}
