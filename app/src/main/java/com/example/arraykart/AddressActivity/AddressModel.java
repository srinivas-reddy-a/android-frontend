package com.example.arraykart.AddressActivity;

public class AddressModel {
    private String fullName;
    private String address;
    private String pinCode;

    public AddressModel(String fullName, String address, String pinCode) {
        this.fullName = fullName;
        this.address = address;
        this.pinCode = pinCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
