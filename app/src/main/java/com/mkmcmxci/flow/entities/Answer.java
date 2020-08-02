package com.mkmcmxci.flow.entities;

import java.util.Date;

public class Answer {

    private String username;
    private String content;
    private String title;
    private int answerCount;
    private boolean isHead;

    public Answer() {
        super();
    }

    public Answer(String username, String content, String title, int answerCount, boolean isHead) {
        this.username = username;
        this.content = content;
        this.title = title;
        this.answerCount = answerCount;
        this.isHead = isHead;
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

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }
}
