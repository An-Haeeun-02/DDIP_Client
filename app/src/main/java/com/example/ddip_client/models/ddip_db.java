package com.example.ddip_client.models;

public class ddip_db {

    private String userid;
    private String userpwd;
    private String username;
    private String email;
    private boolean admin;

    public ddip_db() {}

    public ddip_db(String userid, String userpwd, String username, String email, boolean admin) {
        this.userid = userid;
        this.userpwd = userpwd;
        this.username = username;
        this.email = email;
        this.admin = admin;
    }

    public String getUsername() {
        return userid;
    }

    public void setUsername(String userID) {
        this.userid = userID;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
