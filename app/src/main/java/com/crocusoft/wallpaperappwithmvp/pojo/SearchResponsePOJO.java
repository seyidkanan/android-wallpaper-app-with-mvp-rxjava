package com.crocusoft.wallpaperappwithmvp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponsePOJO {

    @SerializedName("total_pages")
    private int totalPages;

    private List<PhotoPOJO> results;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<PhotoPOJO> getResults() {
        return results;
    }

    public void setResults(List<PhotoPOJO> results) {
        this.results = results;
    }
}
