package com.example.hp.blogapp;

public class TextModel {
    private String Title,Content;

    public TextModel() {
    }


    public TextModel(String title, String content) {
        Title = title;
        Content = content;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
