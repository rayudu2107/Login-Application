package com.jeev.login_application.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jeev.login_application.dto.UpdateRequest;
import com.jeev.login_application.dto.UserResponse;

import jakarta.validation.Valid;

public interface IHomeService {

	ResponseEntity<UserResponse> findUserById(int id);

	ResponseEntity<UserResponse> updateUserById(@Valid UpdateRequest updateRequest, int id);

	void deleteUserById(int id);

	ResponseEntity<List<UserResponse>> findAllUsers();

}
