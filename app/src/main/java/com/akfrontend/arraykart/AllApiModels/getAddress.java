package com.akfrontend.arraykart.AllApiModels;

public class getAddress {
    private int id ;
    private  String user_id;
    private  String address_line1;
    private  String address_line2;
    private  String city;
    private String postal_code;
    private String country;
    private String phone_number;
    private String address_name;

    public getAddress(int id, String user_id, String address_line1, String address_line2, String city,
                      String postal_code, String country, String phone_number, String address_name) {
        this.id = id;
        this.user_id = user_id;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
        this.phone_number = phone_number;
        this.address_name = address_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }
}
