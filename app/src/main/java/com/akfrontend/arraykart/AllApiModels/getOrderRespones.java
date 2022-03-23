package com.akfrontend.arraykart.AllApiModels;

import com.akfrontend.arraykart.MyOrder.OrderItemModel;

import java.util.List;

public class getOrderRespones {
    private List<OrderItemModel> products;

    public getOrderRespones(List<OrderItemModel> products) {
        this.products = products;
    }

    public List<OrderItemModel> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItemModel> products) {
        this.products = products;
    }
}
