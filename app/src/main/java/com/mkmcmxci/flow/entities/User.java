package com.mkmcmxci.flow.entities;

public class User {

    private int id;
    private String name;
    private String mail;
    private String password;
    private int numberOfQuestions;
    private int numberOfAnswers;

    public User() {
        super();
    }

    public User(int id, String name, String mail, String password, int numberOfAnswers, int numberOfQuestions) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.numberOfAnswers = numberOfAnswers;
        this.numberOfQuestions = numberOfQuestions;

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

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
