package com.akfrontend.arraykart.UserProfile;

public class UserProfileModel {
    private String pageName;
    private String pageView;

    public UserProfileModel(String pageName, String pageView) {
        this.pageName = pageName;
        this.pageView = pageView;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageView() {
        return pageView;
    }

    public void setPageView(String pageView) {
        this.pageView = pageView;
    }
}