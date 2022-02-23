package com.example.arraykart.MyCart;

public class CartItemModel {

//    public static final int CART_ITEM = 0;
//    public static final int TOTAL_AMOUNT=1;
//
//    private int type;
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }

    /////cart item
    private String id;
    private String name;
    private  String image ;
    private String price;
    private String quantity;
//    private int freeCoupons;
//    private String cuttedPerice;
//    private int productQuantity;
//    private int offersApplied;
//    private int couponApplied;

    public CartItemModel(String id, String name, String image, String price,String quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity= quantity;
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

    public String getImage() {
        String[] img = image.split(",");
        String i = img[0];
        return "https://arraykartandroid.s3.ap-south-1.amazonaws.com/"+i;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    // ///cart item

//    ////cart total
//    private String totalItems;
//    private String totalItemPrice;
//    private String deliveryPrice;
//    private String savedAmount;
//
//    public CartItemModel(int type, String totalItems, String totalItemPrice, String deliveryPrice, String savedAmount) {
//        this.type = type;
//        this.totalItems = totalItems;
//        this.totalItemPrice = totalItemPrice;
//        this.deliveryPrice = deliveryPrice;
//        this.savedAmount = savedAmount;
//    }
//
//    public String getTotalItems() {
//        return totalItems;
//    }
//
//    public void setTotalItems(String totalItems) {
//        this.totalItems = totalItems;
//    }
//
//    public String getTotalItemPrice() {
//        return totalItemPrice;
//    }
//
//    public void setTotalItemPrice(String totalItemPrice) {
//        this.totalItemPrice = totalItemPrice;
//    }
//
//    public String getDeliveryPrice() {
//        return deliveryPrice;
//    }
//
//    public void setDeliveryPrice(String deliveryPrice) {
//        this.deliveryPrice = deliveryPrice;
//    }
//
//    public String getSavedAmount() {
//        return savedAmount;
//    }
//
//    public void setSavedAmount(String savedAmount) {
//        this.savedAmount = savedAmount;
//    }
//
//    ////cart total
}
