package com.akfrontend.arraykart.AllApiModels;

import com.akfrontend.arraykart.homeCategoryProduct.moreProductCategory.MoreCotegoryModel;

import java.util.List;

public class BrandRespones {
    private List<MoreCotegoryModel> brands;

    public BrandRespones(List<MoreCotegoryModel> brands) {
        this.brands = brands;
    }

    public List<MoreCotegoryModel> getBrands() {
        return brands;
    }

    public void setBrands(List<MoreCotegoryModel> brands) {
        this.brands = brands;
    }
}
