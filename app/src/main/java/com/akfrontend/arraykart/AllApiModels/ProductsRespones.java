package com.akfrontend.arraykart.AllApiModels;

import com.akfrontend.arraykart.homeCategoryProduct.MainModel;

import java.util.List;

public class ProductsRespones {

    private List<MainModel>products;

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
