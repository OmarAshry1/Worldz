package com.example.finallproject;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
class post {
    private String data;
    private int userid;
    private String username;
    private String date;
    private int likescount;
    post(String data, String date, int likescount){
        this.data=data;
        this.date=date;
        this.likescount=likescount;
    }
    post(String data, String date, int likescount,String uername){
        this.data=data;
        this.date=date;
        this.likescount=likescount;
        this.username=uername;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLikescount() {
        return likescount;
    }

    public void setLikescount(int likescount) {
        this.likescount = likescount;
    }
    public String getUsername() {
        return username;
    }

}

