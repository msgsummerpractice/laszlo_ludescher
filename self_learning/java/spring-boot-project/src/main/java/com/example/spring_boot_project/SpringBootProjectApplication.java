package com.example.spring_boot_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.spring_boot_project.model.Role;
import com.example.spring_boot_project.model.User;
import com.example.spring_boot_project.repository.RoleRepo;
import com.example.spring_boot_project.repository.UserRepo;
import com.example.spring_boot_project.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SpringBootProjectApplication {
private static final Logger log = LoggerFactory.getLogger(SpringBootProjectApplication.class);
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootProjectApplication.class, args);
		RoleRepo roleRepo = context.getBean(RoleRepo.class);
        UserRepo userRepo = context.getBean(UserRepo.class);
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
		if (roleRepo.count() == 0) {
			Role adminRole = new Role();
			adminRole.setName("ADMIN");
			roleRepo.save(adminRole);

			Role userRole = new Role();
			userRole.setName("USER");
			roleRepo.save(userRole);

			if (userRepo.findByEmail("admin@example.com").isEmpty()) {
				User admin = new User();
				admin.setEmail("admin@example.com");
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin123")); 
				
				admin.getRoles().add(adminRole);
				admin.getRoles().add(userRole);

				userRepo.save(admin);
			}
		}
	}
}
