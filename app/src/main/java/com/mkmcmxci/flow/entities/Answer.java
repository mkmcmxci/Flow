package com.mkmcmxci.flow.entities;

import java.util.Date;

public class Answer {

    private int id;
    private String content;
    private Date answerDate;
    private Question question;
    private User user;

    public Answer() {
        super();
    }

    public Answer(String content, Date answerDate, Question question, User user) {
        super();
        this.content = content;
        this.answerDate = answerDate;
        this.question = question;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
