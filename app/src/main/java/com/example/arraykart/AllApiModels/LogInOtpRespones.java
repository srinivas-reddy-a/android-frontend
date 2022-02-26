package com.example.arraykart.AllApiModels;

public class LogInOtpRespones {
    private UserId user ;
    private String token;
    private String msg;

    public LogInOtpRespones(String token, String msg,UserId user) {
        this.token = token;
        this.msg = msg;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserId getUser() {
        return user;
    }

    public void setUser(UserId user) {
        this.user = user;
    }
}
