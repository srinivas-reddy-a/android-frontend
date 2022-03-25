package com.akfrontend.arraykart.AllApiModels;

import com.akfrontend.arraykart.homeCategoryProduct.MainModel;

import java.util.List;

public class getProductsRespones {
    private List<MainModel> product;

    public getProductsRespones(List<MainModel> product) {
        this.product = product;
    }

    public List<MainModel> getProduct() {
        return product;
    }

    public void setProduct(List<MainModel> product) {
        this.product = product;
    }
}
