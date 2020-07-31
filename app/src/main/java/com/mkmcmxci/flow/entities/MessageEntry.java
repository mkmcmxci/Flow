package com.mkmcmxci.flow.entities;

import java.util.Date;

public class MessageEntry {

    private int id;
    private String entry;
    private Date date;
    private User user;
    private MessageMain messageMain;

    public MessageEntry() {
        super();
    }

    public MessageEntry(String entry, Date date, User user, MessageMain messageMain) {
        super();
        this.entry = entry;
        this.date = date;
        this.user = user;
        this.messageMain = messageMain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() { return user; }

    public void setUser(User user) {
        this.user = user;
    }

    public MessageMain getMessageMain() {
        return messageMain;
    }

    public void setMessageMain(MessageMain messageMain) {
        this.messageMain = messageMain;
    }
}
