package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class FeedElement {
    private Message message;

    @Autowired
    public FeedElement(@Qualifier("post") Message message) {
        this.message = message;
    }

    public void showFeedElement() {
        this.message.showMessage();
    }
}
