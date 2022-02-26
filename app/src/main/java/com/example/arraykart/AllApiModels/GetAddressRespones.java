package com.example.arraykart.AllApiModels;

import com.example.arraykart.AddressActivity.AddressModel;

import java.util.List;

public class GetAddressRespones {
    private List<AddressModel> address;

    public GetAddressRespones(List<AddressModel> address) {
        this.address = address;
    }

    public List<AddressModel> getAddress() {
        return address;
    }

    public void setAddress(List<AddressModel> address) {
        this.address = address;
    }
}
