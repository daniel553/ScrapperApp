package com.tripletres.scrapperapp.data;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Message model object
 * Created by Daniel on 20/07/2017.
 */

public class Message extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String sender;
    private String body;
    private Date createdAt = new Date();

    public Message() {

    }

    public Message(String msg) {
        this.body = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
