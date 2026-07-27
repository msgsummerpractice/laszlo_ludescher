package com.example;

import org.springframework.stereotype.Component;

@Component
public class Comment implements Message {
    private String body;
    private Post post;
    private User user;
    
    public Comment(String body, Post post, User user) {
        this.body = body;
        this.post = post;
        this.user = user;
    }

    public String getBody() {
        return this.body;
    }

    public void showMessage() {
        System.out.println(this.getBody() + " from " + this.user.getName() + " on post " + this.post.getMessage());
    }
}
