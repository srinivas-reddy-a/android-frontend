package com.example.arraykart.homeCategoryProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainModel {
    int id ;
    String name;
    String price;
    @SerializedName("image")
    @Expose
    String image;

    public MainModel(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
