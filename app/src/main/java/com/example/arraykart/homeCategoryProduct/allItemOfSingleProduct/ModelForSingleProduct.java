package com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct;

public class ModelForSingleProduct {
    private String id;
    private String name ;
    private String price;
//    private String rate;
//    private String ribbon;
    private int imgs;

    public ModelForSingleProduct(String id, String name, String price, int imgs) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public int getImgs() {
        return imgs;
    }

    public void setImgs(int imgs) {
        this.imgs = imgs;
    }
}
