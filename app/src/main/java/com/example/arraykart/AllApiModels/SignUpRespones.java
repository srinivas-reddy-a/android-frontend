package com.example.arraykart.AllApiModels;

public class SignUpRespones {
    private String err;
    private String message;

    public SignUpRespones(String err, String message) {
        this.err = err;
        this.message = message;
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
