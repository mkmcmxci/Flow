package com.mkmcmxci.flow.entities;

public class Conversation {

    public String sender, receiver, lastMessage;
    public int conversationID, senderID, receiverID, lastMessageID;

    public Conversation(String sender, String receiver, String lastMessage, int conversationID, int senderID, int receiverID, int lastMessageID) {
        this.sender = sender;
        this.receiver = receiver;
        this.lastMessage = lastMessage;
        this.conversationID = conversationID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.lastMessageID = lastMessageID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getConversationID() {
        return conversationID;
    }

    public void setConversationID(int conversationID) {
        this.conversationID = conversationID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public int getLastMessageID() {
        return lastMessageID;
    }

    public void setLastMessageID(int lastMessageID) {
        this.lastMessageID = lastMessageID;
    }
}
