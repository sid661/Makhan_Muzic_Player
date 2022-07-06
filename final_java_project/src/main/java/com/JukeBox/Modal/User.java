package com.JukeBox.Modal;

import java.sql.Timestamp;

public class User {
    private int userid;
    private String phonenNo;
    private String name;
    private Timestamp date;

    public User() {}

    public User(int userid, String phonenNo, String name, Timestamp date) {
        this.userid = userid;
        this.phonenNo = phonenNo;
        this.name = name;
        this.date = date;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String  getPhonenNo() {
        return phonenNo;
    }

    public void setPhonenNo(String  phonenNo) {
        this.phonenNo = phonenNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {return date;}

    public void setDate(Timestamp date) {this.date = date;}
}
