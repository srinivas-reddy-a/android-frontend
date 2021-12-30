package com.example.arraykart.AllApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressFormRespones {
    String message;

    public AddressFormRespones(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
