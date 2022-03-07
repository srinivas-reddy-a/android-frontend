package com.example.arraykart.AllApiModels;

public class User {

    private String id ;
    private String name;
    private String phone_number;
    private String email;
    private String is_pradhaan;

    public User(String id, String name, String phone_number, String email, String is_pradhaan) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.is_pradhaan = is_pradhaan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIs_pradhaan() {
        return is_pradhaan;
    }

    public void setIs_pradhaan(String is_pradhaan) {
        this.is_pradhaan = is_pradhaan;
    }
}
