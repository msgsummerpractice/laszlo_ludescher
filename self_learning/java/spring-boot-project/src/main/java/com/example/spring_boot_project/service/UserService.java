package com.example.spring_boot_project.service;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.spring_boot_project.dto.UserResponse;
import com.example.spring_boot_project.model.User;
import com.example.spring_boot_project.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepo userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserResponse create( String username, String email, String password, String firstName, String lastName) {
        log.info("Attempting to create a new user with name: {}", username);
        
        if(this.userRepo.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists!");
        }

        User user = new User(username,  email,  password,  firstName,  lastName);
        this.userRepo.save(user);
        return this.convertToResponse(user);
    }

    public UserResponse update(User newUser, Long id) {
        return this.userRepo.findById(id)
        .map(user -> {
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            return this.convertToResponse(this.userRepo.save(user));
        })
        .orElseGet(() -> {
            return this.convertToResponse(this.userRepo.save(newUser));
        });
    }

    public Page<UserResponse> findAll(int page, int size) {
        Page<User> users = this.userRepo.findAll(PageRequest.of(page, size));
        return users.map(user -> convertToResponse(user));
    }

    public void deleteById(Long id) {
        if (!userRepo.existsById(id)) {
            throw new IllegalArgumentException("There is no user with id "+id);
        }
        userRepo.deleteById(id);
    }

    public UserResponse updatePassword(Long id, String newPassword) {
        return userRepo.findById(id)
        .map(user -> {
            user.setPassword(newPassword);
            userRepo.save(user);
            return convertToResponse(user);
        })
        .orElseGet(() -> {
            throw new IllegalArgumentException("There is no user with id "+id);
        });
    }
    

    public UserResponse findByEmail(String email) {
        Optional<User> userOpt = this.userRepo.findByEmail(email);
        if (this.userRepo.findByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("There is no user with this email");
        }
        return this.convertToResponse(userOpt.get());
    }

    public UserResponse findById(Long id) {
        Optional<User> user = this.userRepo.findById(id);
        
        if (user.isEmpty()) {
            throw new IllegalArgumentException("There is no user with this id");
        }
        return this.convertToResponse(user.get());
    }

    public long count() {
        return this.userRepo.count();
    }

    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        return response;
    }
    
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password) ;
    }
    
}
