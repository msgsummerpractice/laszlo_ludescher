package com.example.spring_boot_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
   @Email(message = "Email must be valid")
    private String email;
    
    @Size(min = 4, max=32, message = "Password must be at least 4 characters")
    private String password;
}
