package com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct;

public class ModelForSingleProduct {
    private String id;
    private String name ;
    private String price;
    private String rate;
    private String ribbon;
    private int imgs;

    public ModelForSingleProduct(String id, String name, String price, String rate, String ribbon, int imgs) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.ribbon = ribbon;
        this.imgs = imgs;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRibbon() {
        return ribbon;
    }

    public void setRibbon(String ribbon) {
        this.ribbon = ribbon;
    }

    public int getImgs() {
        return imgs;
    }

    public void setImgs(int imgs) {
        this.imgs = imgs;
    }
}
