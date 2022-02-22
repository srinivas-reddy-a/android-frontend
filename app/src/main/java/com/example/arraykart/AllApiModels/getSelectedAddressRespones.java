package com.example.arraykart.AllApiModels;

import com.example.arraykart.AddressActivity.AddressModel;

import java.util.List;

public class getSelectedAddressRespones {
    private List<AddressModel> address;

    public getSelectedAddressRespones(List<AddressModel> address) {
        this.address = address;
    }

    public List<AddressModel> getAddress() {
        return address;
    }

    public void setAddress(List<AddressModel> address) {
        this.address = address;
    }
}
