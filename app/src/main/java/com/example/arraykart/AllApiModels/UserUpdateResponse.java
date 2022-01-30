package com.example.arraykart.AllApiModels;

public class UserUpdateResponse {
    UserId user;
    String message;

    public UserUpdateResponse(UserId user, String message) {
        this.user = user;
        this.message = message;
    }

    public UserId getUser() {
        return user;
    }

    public void setUser(UserId user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
