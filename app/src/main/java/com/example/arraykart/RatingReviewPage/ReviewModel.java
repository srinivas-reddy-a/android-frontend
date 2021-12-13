package com.example.arraykart.RatingReviewPage;

import android.widget.TextView;

public class ReviewModel {
    private String rating;
    private String title;
    private String description;
    private String buyerName;
    private String buyerLocation;
    private String buyingDate;

    public ReviewModel(String rating, String title, String description, String buyerName, String buyerLocation, String buyingDate) {
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.buyerName = buyerName;
        this.buyerLocation = buyerLocation;
        this.buyingDate = buyingDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerLocation() {
        return buyerLocation;
    }

    public void setBuyerLocation(String buyerLocation) {
        this.buyerLocation = buyerLocation;
    }

    public String getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(String buyingDate) {
        this.buyingDate = buyingDate;
    }
}
