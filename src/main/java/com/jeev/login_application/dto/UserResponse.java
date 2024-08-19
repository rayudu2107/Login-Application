package com.jeev.login_application.dto;

import com.jeev.login_application.model.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Role role;

}