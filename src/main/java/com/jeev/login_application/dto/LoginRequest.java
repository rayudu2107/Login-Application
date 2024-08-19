package com.jeev.login_application.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private String email;
	private String password;
}
