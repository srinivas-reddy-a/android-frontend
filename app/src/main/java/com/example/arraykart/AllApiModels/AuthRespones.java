package com.example.arraykart.AllApiModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AuthRespones {
    @SerializedName("user")
    private List<User> user;

    public AuthRespones(List<User> user) {
        this.user = user;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
