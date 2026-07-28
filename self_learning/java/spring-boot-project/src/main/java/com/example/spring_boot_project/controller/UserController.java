package com.example.spring_boot_project.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_project.model.UpdateUserPasswordRequest;
import com.example.spring_boot_project.model.User;
import com.example.spring_boot_project.model.UserRequest;
import com.example.spring_boot_project.model.UserResponse;
import com.example.spring_boot_project.service.UserService;

import jakarta.validation.Valid;


@RestController
@Validated
@RequestMapping
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(value="/users", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    })
    ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
  
    @PostMapping(value="/users", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    })
    UserResponse newUser(@Valid @RequestBody UserRequest request) {
        return userService.create(request.getUsername(),request.getEmail(),request.getPassword(),request.getFirstName(), request.getLastName());
    }

    @PutMapping(value="/users/{id}", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    })
    ResponseEntity<UserResponse> newUser(@Valid @RequestBody UserRequest request, @PathVariable Long id) {
        try {
            userService.findById(id);
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(userService.encryptPassword(request.getPassword())); 
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            return ResponseEntity.ok(userService.update(user, id));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.create(request.getUsername(),request.getEmail(),request.getPassword(),request.getFirstName(), request.getLastName())
            );
        }       
    }

    @DeleteMapping(value="/users/{id}", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    })
    ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value="/users/{id}", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    })
    ResponseEntity<UserResponse> updateUserPassword(@PathVariable Long id, @Valid @RequestBody UpdateUserPasswordRequest request) {
        try {
            userService.findById(id);
            return ResponseEntity.ok(userService.updatePassword(id, userService.encryptPassword(request.getPassword())));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }   
    }
}
