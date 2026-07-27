package com.example.spring_boot_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.spring_boot_project.service.UserService;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class SpringBootProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootProjectApplication.class, args);
		UserService userService = context.getBean(UserService.class);
	}
}
