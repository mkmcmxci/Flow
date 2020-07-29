package com.mkmcmxci.flow.entities;

import java.util.List;

public class Category {



    private int id;
    private String name;

    private List<Question> question;

    public Category() {
        super();
    }

    public Category(String name, List<Question> question) {
        super();
        this.name = name;
        this.question = question;
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

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }
}
