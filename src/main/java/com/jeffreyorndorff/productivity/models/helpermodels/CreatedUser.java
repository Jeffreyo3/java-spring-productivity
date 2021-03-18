package com.jeffreyorndorff.productivity.models.helpermodels;

public class CreatedUser {
    private long userid;
    private String username;

    public CreatedUser(long userid, String username) {
        this.userid = userid;
        this.username = username;
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
}
