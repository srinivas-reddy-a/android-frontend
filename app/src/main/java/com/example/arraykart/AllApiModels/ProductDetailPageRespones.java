package com.example.arraykart.AllApiModels;

import com.example.arraykart.ProductDetailAboutListing.ProductDetailPageModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailPageRespones {
    @SerializedName("product")
    List<ProductDetailPageModel> product;

    public ProductDetailPageRespones(List<ProductDetailPageModel> product) {
        this.product = product;
    }

    public List<ProductDetailPageModel> getProduct() {
        return product;
    }

    public void setProduct(List<ProductDetailPageModel> product) {
        this.product = product;
    }
}
