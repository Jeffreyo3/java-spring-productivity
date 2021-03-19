package com.jeffreyorndorff.productivity.models.helpermodels;

public class SimpleRecipeAuthor {
    private long userid;
    private String username;
    private String fname;
    private String lname;

    public SimpleRecipeAuthor(long userid, String username, String fname, String lname) {
        this.userid = userid;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
