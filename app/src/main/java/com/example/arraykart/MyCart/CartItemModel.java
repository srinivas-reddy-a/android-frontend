package com.example.arraykart.MyCart;

public class CartItemModel {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT=1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /////cart item
    private String id;
    private  int productImage ;
    private String productTitle;
    private int freeCoupons;
    private String productPrice;
    private String cuttedPerice;
    private int productQuantity;
    private int offersApplied;
    private int couponApplied;

    public CartItemModel(int type, String id,int productImage, String productTitle, int freeCoupons, String productPrice,String cuttedPerice,int productQuantity,int offersApplied,int couponApplied) {
        this.type = type;
        this.id = id;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCoupons = freeCoupons;
        this.productPrice = productPrice;
        this.cuttedPerice = cuttedPerice;
        this.productQuantity = productQuantity;
        this.offersApplied = offersApplied;
        this.couponApplied = couponApplied;
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

    public int getFreeCoupons() {
        return freeCoupons;
    }

    public void setFreeCoupons(int freeCoupons) {
        this.freeCoupons = freeCoupons;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPerice() {
        return cuttedPerice;
    }

    public void setCuttedPerice(String cuttedPerice) {
        this.cuttedPerice = cuttedPerice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getOffersApplied() {
        return offersApplied;
    }

    public void setOffersApplied(int offersApplied) {
        this.offersApplied = offersApplied;
    }

    public int getCouponApplied() {
        return couponApplied;
    }

    public void setCouponApplied(int couponApplied) {
        this.couponApplied = couponApplied;
    }

    // ///cart item

    ////cart total
    private String totalItems;
    private String totalItemPrice;
    private String deliveryPrice;
    private String savedAmount;

    public CartItemModel(int type, String totalItems, String totalItemPrice, String deliveryPrice, String savedAmount) {
        this.type = type;
        this.totalItems = totalItems;
        this.totalItemPrice = totalItemPrice;
        this.deliveryPrice = deliveryPrice;
        this.savedAmount = savedAmount;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(String totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }

    ////cart total
}
