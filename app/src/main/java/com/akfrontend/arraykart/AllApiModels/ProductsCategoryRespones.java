package com.akfrontend.arraykart.AllApiModels;

import com.akfrontend.arraykart.homeCategoryProduct.moreProductCategory.MoreCotegoryModel;

import java.util.List;

public class ProductsCategoryRespones {

    private List<MoreCotegoryModel> categories;

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
