package com.mkmcmxci.flow.entities;

import java.util.Date;
import java.util.List;

public class Message {

    private String date;
    private String reply;
    private String username;

    public Message() {
        super();
    }

    public Message(String date, String reply, String username) {
        this.date = date;
        this.reply = reply;
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}




