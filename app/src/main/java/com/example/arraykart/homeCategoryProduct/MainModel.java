package com.example.arraykart.homeCategoryProduct;

public class MainModel {
    String name,price;
    int imgs;

    public MainModel(String name, String price, int imgs) {
        this.name = name;
        this.price = price;
        this.imgs = imgs;
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

    public int getImgs() {
        return imgs;
    }

    public void setImgs(int imgs) {
        this.imgs = imgs;
    }
}
