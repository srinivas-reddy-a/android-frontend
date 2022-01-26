package com.example.arraykart.WishList;

public class WishListModel {
    private String id;
    private int productImage;
    private String productTitle;
    private int freeOffer;
    private String rating;
    private String productPrice;
    private String cuttedPrice;
    private String paymentMethod;

    public WishListModel(String id,int productImage, String productTitle,  int freeOffer, String rating, String productPrice, String cuttedPrice, String paymentMethod) {
        this.id = id;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeOffer = freeOffer;
        this.rating = rating;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
        this.paymentMethod = paymentMethod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getFreeOffer() {
        return freeOffer;
    }

    public void setFreeOffer(int freeOffer) {
        this.freeOffer = freeOffer;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
