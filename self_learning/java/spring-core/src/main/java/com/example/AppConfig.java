package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class AppConfig {
    @Bean
    public User user() {
        User user = new User("Bob", "admin1234");
        return user;
    }

    @Bean
    public Comment comment() {
        User user1 = new User("Bob", "admin1234");
        User user2 = new User("Joe", "admin1234");

        Post post = new Post(user1);
        Comment comment = new Comment("comment body",post, user2);

        return comment;
    }
}
