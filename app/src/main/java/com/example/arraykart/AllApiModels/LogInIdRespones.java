package com.example.arraykart.AllApiModels;

public class LogInIdRespones {
    public UserId user ;

    public LogInIdRespones(UserId user) {
        this.user = user;
    }

    public UserId getUser() {
        return user;
    }

    public void setUser(UserId user) {
        this.user = user;
    }
}
