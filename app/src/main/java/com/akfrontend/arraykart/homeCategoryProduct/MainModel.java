package com.akfrontend.arraykart.homeCategoryProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainModel {
    private String id ;
    private String name;
    private String price;
    @SerializedName("image")
    @Expose
    private String image;

    public MainModel(String id, String name, String price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        String[] img = image.split(",");
        String i = img[0];
        return "https://arraykartandroid.s3.ap-south-1.amazonaws.com/"+i;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
