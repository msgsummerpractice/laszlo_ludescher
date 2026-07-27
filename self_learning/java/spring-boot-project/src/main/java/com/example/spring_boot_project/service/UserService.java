package com.example.spring_boot_project.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.spring_boot_project.model.User;
import com.example.spring_boot_project.repository.UserRepo;

@Component
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserRepo getUserRepo() {
        return this.userRepo;
    }

    public void createUser(String name, String pwd, int id) {
        log.info("Attempting to create a new user with name: {}", name);

        if (pwd.length() < 4) {
            throw new IllegalArgumentException("User password must be at least 4 characters!");
        }

        if (this.userRepo.existsUserWithId(id)) {
            throw new IllegalArgumentException("User id must be unique");
        }
        User user = new User(name, pwd, id);
        this.userRepo.addUser(user);
    }

    public List<User> getUsers() {
        return this.userRepo.getUsers();
    }
}
