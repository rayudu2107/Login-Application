package com.jeev.login_application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeev.login_application.dto.UpdateRequest;
import com.jeev.login_application.dto.UserResponse;
import com.jeev.login_application.service.IHomeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
public class HomeController {
	private final IHomeService homeService;
	
	// Finds a user by their ID.
	@GetMapping("{id}")
	public ResponseEntity<UserResponse> findUserById(@PathVariable int id) {
		return homeService.findUserById(id);
	}
	
	// Updates a user's details by their ID
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<UserResponse> updateUserById(@Valid @RequestBody UpdateRequest updateRequest,@PathVariable int id) {
		return homeService.updateUserById(updateRequest,id);
	}
	
	// Deletes a user by their ID
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        homeService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Finds all users
	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> findAllUsers() {
		return homeService.findAllUsers();
		 
	}
}
