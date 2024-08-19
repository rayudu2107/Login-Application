package com.jeev.login_application.dto;

import com.jeev.login_application.model.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

	@NotEmpty(message = "First name cannot be blank & not blank")
	@Size(min = 5, max = 20, message = "First name must be between 5 and 20 characters")
	@Pattern(regexp = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)?$", message = "Username should follow initcap format")
	private String firstName;

	private String lastName;

	@NotEmpty(message = "email cannot be not null & not blank")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;

	@NotEmpty(message = "Phone number cannot be null or blank")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
	private String phoneNumber;

	@NotEmpty(message = "Password cannot be not null & not blank")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	private String password;
	
	@NotNull(message = "Role is required")
	@Enumerated(EnumType.STRING)
	private Role role;
}
