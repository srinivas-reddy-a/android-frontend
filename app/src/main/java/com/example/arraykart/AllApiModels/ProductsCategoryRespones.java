package com.example.arraykart.AllApiModels;

import com.example.arraykart.homeCategoryProduct.moreProductCategory.MoreCotegoryModel;

import java.util.List;

public class ProductsCategoryRespones {

    List<MoreCotegoryModel> categories;

    public ProductsCategoryRespones(List<MoreCotegoryModel> categories) {
        this.categories = categories;
    }

    public List<MoreCotegoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<MoreCotegoryModel> categories) {
        this.categories = categories;
    }
}
