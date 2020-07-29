package com.mkmcmxci.flow.entities;

import java.util.Date;
import java.util.List;

public class Question {

    private int id;
    private String title;
    private String content;
    private String username;
    private int answerSize;

    public Question() {
        super();
    }

    public Question(int id, String title, String content, String username, int answerSize) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.answerSize = answerSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAnswerSize() {
        return answerSize;
    }

    public void setAnswerSize(int answerSize) {
        this.answerSize = answerSize;
    }
}
