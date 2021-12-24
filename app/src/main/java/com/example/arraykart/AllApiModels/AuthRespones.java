package com.example.arraykart.AllApiModels;

public class AuthRespones {
    User user;

    public AuthRespones(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
