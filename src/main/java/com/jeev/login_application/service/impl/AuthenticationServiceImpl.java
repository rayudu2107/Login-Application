package com.jeev.login_application.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.jeev.login_application.dto.JwtAuthenticationResponse;
import com.jeev.login_application.dto.LoginRequest;
import com.jeev.login_application.dto.RegisterRequest;
import com.jeev.login_application.dto.UserResponse;
import com.jeev.login_application.exceptions.BadCredentialsException;
import com.jeev.login_application.exceptions.UserAlreadyExistsByEmailException;
import com.jeev.login_application.mapper.UserMapper;
import com.jeev.login_application.model.User;
import com.jeev.login_application.repository.IUserRepository;
import com.jeev.login_application.security.JwtService;
import com.jeev.login_application.service.IAuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

	private final IUserRepository userRepository;
	private final UserMapper userMapper;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	// Registers a new user
	public ResponseEntity<UserResponse> register(@Valid RegisterRequest registerRequest) {
		Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsByEmailException(
					"A user with the email " + registerRequest.getEmail() + " already exists.");
		}

		User user = userMapper.mapToUser(registerRequest);

		User savedUser = userRepository.save(user);

		UserResponse userResponse = userMapper.mapToUserResponse(savedUser);

		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}

	// Authenticates a user
	public ResponseEntity<JwtAuthenticationResponse> login(LoginRequest loginRequest) {
		try {
			// Attempt to authenticate the user with provided credentials
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		} catch (BadCredentialsException ex) {
			// Catch the Spring Security exception and throw your custom exception
			throw new BadCredentialsException("Invalid username or password");
		}
		// Retrieve user details from the repository
		var user = userRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

		// Generate JWT token
		var jwt = jwtService.generateToken(user);

		// Prepare and return the authentication response
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(jwt);
		return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
	}

}
