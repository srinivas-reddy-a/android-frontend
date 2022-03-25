package com.akfrontend.arraykart.AllApiModels;

import com.akfrontend.arraykart.ProductDetailAboutListing.ProductDetailPageModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailPageRespones {
    @SerializedName("product")
    private List<ProductDetailPageModel> product;

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
