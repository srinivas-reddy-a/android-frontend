package com.example.arraykart.AllApiModels;

import com.example.arraykart.SearchPage.SearchProductModel;

import java.util.List;

public class SearchProducRespones {
    private List<SearchProductModel> products;

    public SearchProducRespones(List<SearchProductModel> products) {
        this.products = products;
    }

    public List<SearchProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<SearchProductModel> products) {
        this.products = products;
    }
}
