package com.akfrontend.arraykart.AllApiModels;

import com.akfrontend.arraykart.homeCategoryProduct.nestedModel;

import java.util.List;

public class nestedCategoryRespones {
    private List<nestedModel> categories;

    public nestedCategoryRespones(List<nestedModel> categories) {
        this.categories = categories;
    }

    public List<nestedModel> getCategories() {
        return categories;
    }

    public void setCategories(List<nestedModel> categories) {
        this.categories = categories;
    }
}
