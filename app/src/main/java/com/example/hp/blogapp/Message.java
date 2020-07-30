package com.example.hp.blogapp;

/**
 * Created by VMac on 17/11/16.
 */

import java.io.Serializable;

public class Message implements Serializable {
    String id, message;
    String url;


    public Message() {
    }

    public Message(String id, String message, String createdAt, String url) {
        this.id = id;
        this.message = message;
         this.url=url;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

