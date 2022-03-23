package com.akfrontend.arraykart.AllApiModels;

import com.akfrontend.arraykart.MyCart.CartItemModel;

import java.util.List;

public class GetCartRespones {
    private List<CartItemModel> products;

    public GetCartRespones(List<CartItemModel> products) {
        this.products = products;
    }

    public List<CartItemModel> getProducts() {
        return products;
    }

    public void setProducts(List<CartItemModel> products) {
        this.products = products;
    }
}
