package com.example.arraykart.AllApiModels;

import com.example.arraykart.WishList.WishListModel;

import java.util.List;

public class getWishListRespones {
    private List<WishListModel> products;

    public getWishListRespones(List<WishListModel> products) {
        this.products = products;
    }

    public List<WishListModel> getProducts() {
        return products;
    }

    public void setProducts(List<WishListModel> products) {
        this.products = products;
    }
}
