package com.mkmcmxci.flow.entities;

import java.util.Date;

public class Answer {

    private String username;
    private String content;
    private String title;
    private int answerCount;
    private int answerUserID;
    private int userQuestionSize;
    private int userAnswerSize;
    private int QuestionID;
    private int QuestionUserID;
    private int answerID;
    private String questionContent;
    private String questionUsername;

    public Answer() {
        super();
    }

    public Answer(String username, String content, String title, int answerCount, int answerUserID,
                  int userQuestionSize, int userAnswerSize, int QuestionID, int QuestionUserID, int answerID, String questionContent, String questionUsername) {

        this.username = username;
        this.content = content;
        this.title = title;
        this.answerCount = answerCount;
        this.answerUserID = answerUserID;
        this.userQuestionSize = userQuestionSize;
        this.userAnswerSize = userAnswerSize;
        this.QuestionID = QuestionID;
        this.QuestionUserID = QuestionUserID;
        this.answerID = answerID;
        this.questionContent = questionContent;
        this.questionUsername = questionUsername;
    }


    public String getQuestionUsername() {
        return questionUsername;
    }

    public void setQuestionUsername(String questionUsername) {
        this.questionUsername = questionUsername;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getQuestionUserID() {
        return QuestionUserID;
    }

    public void setQuestionUserID(int questionUserID) {
        QuestionUserID = questionUserID;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
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

    public int getAnswerUserID() {
        return answerUserID;
    }

    public void setAnswerUserID(int answerUserID) {
        this.answerUserID = answerUserID;
    }

    public int getUserQuestionSize() {
        return userQuestionSize;
    }

    public void setUserQuestionSize(int userQuestionSize) {
        this.userQuestionSize = userQuestionSize;
    }

    public int getUserAnswerSize() {
        return userAnswerSize;
    }

    public void setUserAnswerSize(int userAnswerSize) {
        this.userAnswerSize = userAnswerSize;
    }

}
