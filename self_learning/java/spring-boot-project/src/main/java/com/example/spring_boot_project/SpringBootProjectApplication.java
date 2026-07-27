package com.example.spring_boot_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.spring_boot_project.repository.InMemoryUserRepo;
import com.example.spring_boot_project.repository.UserRepo;
import com.example.spring_boot_project.service.UserService;


@SpringBootApplication
@EnableConfigurationProperties(AppSettings.class)
public class SpringBootProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
		InMemoryUserRepo repo = new InMemoryUserRepo();
		UserService service = new UserService(repo);
		service.createUser("Bob", "admin1234", 11);
		//System.out.println(appSetings.ip);
	}
}
