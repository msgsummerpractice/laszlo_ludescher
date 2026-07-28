package com.example.spring_boot_project.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginRequest {
   @Email(message = "Email must be valid")
    private String email;
}
