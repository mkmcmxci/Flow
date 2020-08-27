package com.mkmcmxci.flow.entities;


public class Question {

    private int id;
    private String title;
    private String content;
    private String username;
    private int answerSize;
    private int questionUserID;
    private int userQuestionSize;
    private int userAnswerSize;

    public Question() {
        super();
    }

    public Question(int id,
                    String title,
                    String content,
                    String username,
                    int answerSize,
                    int questionUserID,
                    int userQuestionSize,
                    int userAnswerSize) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.answerSize = answerSize;
        this.questionUserID = questionUserID;
        this.userQuestionSize = userQuestionSize;
        this.userAnswerSize = userAnswerSize;

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

    public int getQuestionUserID() {
        return questionUserID;
    }

    public void setQuestionUserID(int questionUserID) {
        this.questionUserID = questionUserID;
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
