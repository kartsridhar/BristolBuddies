package com.example.karthik.mvp.Activity;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int id;
    private String fname;
    private String lname;
    private String gender;
    private String uname;
    private String pwd;
    private String dept;
    private String yos;

    @SerializedName("body")
    private String text;

    public Post(String fname, String lname, String gender,
                 String uname, String pwd, String dept, String yos) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.uname = uname;
        this.pwd = pwd;
        this.dept = dept;
        this.yos = yos;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGender() {
        return gender;
    }

    public String getUname() {
        return uname;
    }

    public String getPwd() {
        return pwd;
    }

    public String getDept() {
        return dept;
    }

    public String getYos() {
        return yos;
    }
 }
