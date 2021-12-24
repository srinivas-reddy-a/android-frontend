package com.example.arraykart.AllApiModels;

import com.example.arraykart.homeCategoryProduct.MainModel;

import java.util.List;

public class ProductsRespones {

    List<MainModel> products;

    public ProductsRespones(List<MainModel> products) {
        this.products = products;
    }

    public List<MainModel> getProducts() {
        return products;
    }

    public void setProducts(List<MainModel> products) {
        this.products = products;
    }
}
