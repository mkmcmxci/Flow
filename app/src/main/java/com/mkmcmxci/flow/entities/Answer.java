package com.mkmcmxci.flow.entities;

import java.util.Date;

public class Answer {

    private String username;
    private String content;
    private String title;
    private int answerCount;

    public Answer() {
        super();
    }

    public Answer(String username, String content, String title, int answerCount) {
        this.username = username;
        this.content = content;
        this.title = title;
        this.answerCount = answerCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", answerCount=" + answerCount +
                '}';
    }
}
