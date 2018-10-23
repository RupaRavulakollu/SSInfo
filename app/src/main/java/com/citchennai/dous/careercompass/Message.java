package com.citchennai.dous.careercompass;

/**
 * Created by Niru.R on 02-04-2018.
 */

public class Message {
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String content;
    private String username;
    private String uid;
    public Message(){}

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
}
