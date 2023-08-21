package com.example.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Headlines {

    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("totalResults")
    @Expose
    private String totalResults;


    @SerializedName("articles")
    @Expose
    private List<Articles> articles;

    public Headlines(String status, String totalResults, List<Articles> article) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = article;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticle() {
        return articles;
    }

    public void setArticle(List<Articles> article) {
        this.articles = article;
    }
}
