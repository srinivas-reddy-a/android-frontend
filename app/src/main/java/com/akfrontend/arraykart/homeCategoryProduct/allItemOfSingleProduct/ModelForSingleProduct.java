package com.akfrontend.arraykart.homeCategoryProduct.allItemOfSingleProduct;

public class ModelForSingleProduct {
    private String id;
    private String name ;
    private String price;
//    private String rate;
//    private String ribbon;
    private String image;

    public ModelForSingleProduct(String id, String name, String price, String imgs) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = imgs;
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

    public String getImgs() {
        String[] img = image.split(",");
        String i = img[0];
        return "https://arraykartandroid.s3.ap-south-1.amazonaws.com/"+i;
    }

    public void setImgs(String image) {
        this.image = image;
    }
}
