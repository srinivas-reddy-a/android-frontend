package com.example.arraykart.ProductDetailAboutListing;

public class ProductDetailListingModel {

    String ListName;
    int ListImage;
    String ListDetail;

    public ProductDetailListingModel(String listName, int listImage, String listDetail) {
        ListName = listName;
        ListImage = listImage;
        ListDetail = listDetail;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public int getListImage() {
        return ListImage;
    }

    public void setListImage(int listImage) {
        ListImage = listImage;
    }

    public String getListDetail() {
        return ListDetail;
    }

    public void setListDetail(String listDetail) {
        ListDetail = listDetail;
    }
}
