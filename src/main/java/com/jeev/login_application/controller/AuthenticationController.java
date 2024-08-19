package com.jeev.login_application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jeev.login_application.dto.JwtAuthenticationResponse;
import com.jeev.login_application.dto.LoginRequest;
import com.jeev.login_application.dto.RegisterRequest;
import com.jeev.login_application.dto.UserResponse;
import com.jeev.login_application.service.IAuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	private final IAuthenticationService authenticationService;

	// Registers a new user
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
		return  authenticationService.register(registerRequest);
	}

	// Authenticates a user
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
		return authenticationService.login(loginRequest);
	
	}

}
