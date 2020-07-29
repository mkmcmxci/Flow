package com.mkmcmxci.flow.entities;

import java.util.Date;
import java.util.List;

public class MessageMain {

    private int id;
    private Date date;
    private int senderId;
    private int receiverId;

    private List<User> users;

    private List<MessageEntry> entries;

    public MessageMain() {
        super();
    }

    public MessageMain(Date date, int senderId, int receiverId, List<User> users, List<MessageEntry> entries) {
        super();
        this.date = date;
        this.users = users;
        this.entries = entries;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<MessageEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<MessageEntry> entries) {
        this.entries = entries;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

}
