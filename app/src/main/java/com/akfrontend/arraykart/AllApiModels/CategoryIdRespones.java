package com.akfrontend.arraykart.AllApiModels;

import com.akfrontend.arraykart.homeCategoryProduct.allItemOfSingleProduct.ModelForSingleProduct;

import java.util.List;

public class CategoryIdRespones {

   private List<ModelForSingleProduct> products;

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
