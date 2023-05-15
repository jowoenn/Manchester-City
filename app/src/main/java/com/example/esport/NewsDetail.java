package com.example.esport;

public class NewsDetail {

    private String newsName;
    private String newsTime;
    private String newsContent;

    public NewsDetail(String newsName, String newsTime, String newsContent) {
        this.newsName = newsName;
        this.newsTime = newsTime;
        this.newsContent = newsContent;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}
