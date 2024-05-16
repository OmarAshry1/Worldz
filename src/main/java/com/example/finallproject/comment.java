package com.example.finallproject;

import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
class comment {

    private String data;
    private String commentorname;
    public comment (String data,String commentorname) {
        this.data = data;
        this.commentorname = commentorname;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCommentorname() {
        return commentorname;
    }

    public void setCommentorname(String commentorname) {
        this.commentorname = commentorname;
    }
}

