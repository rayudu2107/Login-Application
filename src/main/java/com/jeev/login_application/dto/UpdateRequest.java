package com.jeev.login_application.dto;

import jakarta.validation.constraints.NotEmpty;
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
public class UpdateRequest {
	@NotEmpty(message = "First name cannot be blank & not blank")
	@Size(min = 5, max = 20, message = "First name must be between 5 and 20 characters")
	@Pattern(regexp = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)?$", message = "Username should follow initcap format")
	private String firstName;

	private String lastName;

	@NotEmpty(message = "Phone number cannot be null or blank")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
	private String phoneNumber;


}
