package com.example.spring_boot_project.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.spring_boot_project.model.User;
import com.example.spring_boot_project.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser( String username, String email, String password, String firstName, String lastName) {
        log.info("Attempting to create a new user with name: {}", username);

        if (password.length() < 4) {
            throw new IllegalArgumentException("User password must be at least 4 characters!");
        }
        
        if(this.userRepo.findByEmail(email)!=null) {
            throw new IllegalArgumentException("User with this email already exists!");
        }

        User user = new User(username,  email,  password,  firstName,  lastName);
        this.userRepo.save(user);
    }

    public Page<User> findAll() {
        return this.userRepo.findAll(PageRequest.of(0, 10));
    }

    public void deleteById(Long id) {
        if (!this.userRepo.existsById(id)) {
            throw new IllegalArgumentException("There is no user with id "+id);
        }
        this.userRepo.deleteById(id);
    }

    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    public long count() {
        return this.userRepo.count();
    }
}
