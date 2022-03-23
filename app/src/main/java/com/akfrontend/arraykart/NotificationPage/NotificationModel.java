package com.akfrontend.arraykart.NotificationPage;
public class NotificationModel {
    private int notiImage;
    private String boldText;
    private String normalText;

    public NotificationModel(int notiImage, String boldText, String normalText) {
        this.notiImage = notiImage;
        this.boldText = boldText;
        this.normalText = normalText;
    }

    public int getNotiImage() {
        return notiImage;
    }

    public void setNotiImage(int notiImage) {
        this.notiImage = notiImage;
    }

    public String getBoldText() {
        return boldText;
    }

    public void setBoldText(String boldText) {
        this.boldText = boldText;
    }

    public String getNormalText() {
        return normalText;
    }

    public void setNormalText(String normalText) {
        this.normalText = normalText;
    }
}
