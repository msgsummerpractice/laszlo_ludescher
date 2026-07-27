package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Post implements Message {

    private String message;
    private User user;
    
    @Autowired
    public Post(User user) {
        this.message = "message";
        this.user = user;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void showMessage() {
        System.out.println(this.getMessage() + "from" + this.user.getName());
    }
}
