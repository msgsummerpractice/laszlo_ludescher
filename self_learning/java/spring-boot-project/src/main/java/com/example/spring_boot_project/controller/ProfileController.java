package com.example.spring_boot_project.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
   @Value("${spring.profiles.active:default}")
   private String activeProfile;
   @GetMapping("/active-profile")
   public String getActiveProfile() {
       return "Active Profile: " + activeProfile;
   }
}
