package com.example.arraykart.AllApiModels;

import com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct.ModelForSingleProduct;

import java.util.List;

public class CategoryIdRespones {

    List<ModelForSingleProduct> products;

    public CategoryIdRespones(List<ModelForSingleProduct> modelForSingleProducts) {
        this.products = modelForSingleProducts;
    }

    public List<ModelForSingleProduct> getProducts() {
        return products;
    }

    public void setCategoryDetail(List<ModelForSingleProduct> products) {
        this.products = products;
    }
}
