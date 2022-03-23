package com.akfrontend.arraykart.WishList;

public class WishListModel {
    private String id;
    private String image;
    private String name;
    private String price;
//    private int freeOffer;
//    private String rating;
//    private String cuttedPrice;
//    private String paymentMethod;


    public WishListModel(String id, String image, String name, String price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        String[] img = image.split(",");
        String i = img[0];
        return "https://arraykartandroid.s3.ap-south-1.amazonaws.com/"+i;
    }

    public void setImage(String image) {
        this.image = image;
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
}
