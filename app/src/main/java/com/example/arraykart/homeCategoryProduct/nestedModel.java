package com.example.arraykart.homeCategoryProduct;

import java.util.List;

public class nestedModel {
    private String id ;
    private String name;
    //private List<MainModel> products;

//    public nestedModel(String name, List<MainModel> products) {
//        this.name = name;
//        this.products = products;
//    }


    public nestedModel(String name,String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //    public List<MainModel> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<MainModel> products) {
//        this.products = products;
//    }
}
