package com.example.arraykart.MyOrder;

public class OrderItemModel {
    private String order_id;
    private String products_id;
    private String quantity;
    private String delivery_date;
    private String delivery_type;
    private String address_id;
    private String volume;

//    private int rating;
//    private String productTitle;
//    private String deliveryStatus;


    public OrderItemModel(String order_id, String products_id, String quantity, String delivery_date, String delivery_type, String address_id,String volume) {
        this.order_id = order_id;
        this.products_id = products_id;
        this.quantity = quantity;
        this.delivery_date = delivery_date;
        this.delivery_type = delivery_type;
        this.address_id = address_id;
        this.volume = volume;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
