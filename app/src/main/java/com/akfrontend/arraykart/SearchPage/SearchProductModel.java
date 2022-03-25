package com.akfrontend.arraykart.SearchPage;

public class SearchProductModel {
    private String id;
    private String name;
    private String price;
    private String image;

    public SearchProductModel(String id, String name, String price, String image) {
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
