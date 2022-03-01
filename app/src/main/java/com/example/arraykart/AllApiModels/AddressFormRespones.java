package com.example.arraykart.AllApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressFormRespones {
    private String msg;

    public AddressFormRespones(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
