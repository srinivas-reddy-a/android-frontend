package com.example.arraykart.AllApiModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AuthRespones {
    @SerializedName("user")
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
