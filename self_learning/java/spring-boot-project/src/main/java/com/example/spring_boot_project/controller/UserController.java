package com.example.spring_boot_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_project.AppSettings;
import com.example.spring_boot_project.model.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Max;

@RestController
@Validated
public class UserController {

    @Value("${server.port}")
    private String port;

    @Value("${app.settings.ip}")
    private String ip;
    
    @GetMapping("/users")
    public String getUserMessage(
        @RequestParam
        @NotEmpty(message = "User id must be specified")
        @Min(value = 1000, message = "Id must be at least 1000")
        @Max(value = 9999, message = "Id must be smaller or equal to 9999")
        String id
    ) {
        return "Hello there! Your id is: " + id + " (This app is running on port "+port+ " and ip "+ip+")";
    }

    /*
    @PostMapping("/users")
    ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok("User is valid");
    }
        */
}
