package com.akfrontend.arraykart.AllApiModels;

public class LogInIdRespones {
    private User user;

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
