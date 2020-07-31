package com.mkmcmxci.flow.entities;

import java.util.Date;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String mail;
    private String password;
    private Date joinDate;
    private List<Question> questions;
    private List<Answer> answers;
    private List<MessageMain> messageMains;
    private List<MessageEntry> messageEntries;

    public User() {
        super();
    }

    public User(String name, String mail, String password, Date joinDate, List<Question> questions,
                List<Answer> answers, List<MessageMain> messageMains, List<MessageEntry> messageEntries) {
        super();
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.joinDate = joinDate;
        this.questions = questions;
        this.answers = answers;
        this.messageMains = messageMains;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<MessageMain> getMessageMains() {
        return messageMains;
    }

    public void setMessageMains(List<MessageMain> messageMains) {
        this.messageMains = messageMains;
    }

    public List<MessageEntry> getMessageEntries() {
        return messageEntries;
    }

    public void setMessageEntries(List<MessageEntry> messageEntries) {
        this.messageEntries = messageEntries;
    }

}
