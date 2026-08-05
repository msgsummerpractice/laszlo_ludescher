package com.example.spring_boot_project.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token, UserDetails userDetails);
    public String extractUsername(String token);
    public long getExpirationTime();
}
