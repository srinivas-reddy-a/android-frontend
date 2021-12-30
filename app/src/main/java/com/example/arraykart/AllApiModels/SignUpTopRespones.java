package com.example.arraykart.AllApiModels;

public class SignUpTopRespones {
    private String token;
    private  String err;
    private String message;

    public SignUpTopRespones(String token, String err, String message) {
        this.token = token;
        this.err = err;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
