package com.example.arraykart.AllApiModels;

public class LogInIdRespones {
    User user;

    public LogInIdRespones(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
