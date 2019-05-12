package com.test.thread.queue.bo;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private String data;

    private Date date;

    public Message(String data) {
        this.data = data;
        this.date = new Date();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
