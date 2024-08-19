package com.jeev.login_application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jeev.login_application.dto.UpdateRequest;
import com.jeev.login_application.dto.UserResponse;
import com.jeev.login_application.exceptions.UserNotFoundByIdException;
import com.jeev.login_application.exceptions.UsersDataNotFoundException;
import com.jeev.login_application.mapper.UserMapper;
import com.jeev.login_application.model.User;
import com.jeev.login_application.repository.IUserRepository;
import com.jeev.login_application.service.IHomeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements IHomeService {
	private final IUserRepository userRepository;
	private final UserMapper userMapper;

	// Finds a user by their ID.
	public ResponseEntity<UserResponse> findUserById(int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found for ID " + id));
		UserResponse userResponse = userMapper.mapToUserResponse(user);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	// Deletes a user's details by their ID
	public void deleteUserById(int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found to delete for ID " + id));
		userRepository.delete(user);
	}

	// Updates a user by their ID
	public ResponseEntity<UserResponse> updateUserById(@Valid UpdateRequest updateRequest, int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found to update for ID " + id));
		user.setFirstName(updateRequest.getFirstName());
		user.setLastName(updateRequest.getLastName());
		user.setPhoneNumber(updateRequest.getPhoneNumber());
		user.setId(id);
		User updateUser = userRepository.save(user);
		UserResponse userResponse = userMapper.mapToUserResponse(updateUser);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	// Finds all users
	public ResponseEntity<List<UserResponse>> findAllUsers() {
		List<User> userList = userRepository.findAll();
		if (!userList.isEmpty()) {
			List<UserResponse> list = new ArrayList<>();
			for (User user : userList) {
				UserResponse userResponse = userMapper.mapToUserResponse(user);
				list.add(userResponse);
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else
			throw new UsersDataNotFoundException("Users Data Not Present to Fetch");
	}
}
