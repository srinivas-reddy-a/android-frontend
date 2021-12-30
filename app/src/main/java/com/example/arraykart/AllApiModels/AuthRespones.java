package com.example.arraykart.AllApiModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AuthRespones {
    @SerializedName("user")
    UserId user;

    public AuthRespones(UserId user) {
        this.user = user;
    }

    public UserId getUser() {
        return user;
    }

    public void setUser(UserId user) {
        this.user = user;
    }
}
