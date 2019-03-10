package com.crocusoft.wallpaperappwithmvp.pojo;

public class PhotoPOJO {

    private String id;
    private String description;
    private LocationPOJO location;
    private Urls urls;

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocationPOJO getLocation() {
        return location;
    }

    public void setLocation(LocationPOJO location) {
        this.location = location;
    }
}
