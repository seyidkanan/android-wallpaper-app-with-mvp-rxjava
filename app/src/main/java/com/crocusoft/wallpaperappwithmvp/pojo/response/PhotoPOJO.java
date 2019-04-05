package com.crocusoft.wallpaperappwithmvp.pojo.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotoPOJO implements Serializable {

    private String id;
    private String description;
    private LocationPOJO location;
    private Urls urls;
    private ImageTechnicalInfoPOJO exif;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    private Integer width;
    private Integer height;
    private String views;
    private String downloads;
    private String likes;
    private AuthorPOJO user;

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

    public ImageTechnicalInfoPOJO getExif() {
        return exif;
    }

    public void setExif(ImageTechnicalInfoPOJO exif) {
        this.exif = exif;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public AuthorPOJO getUser() {
        return user;
    }

    public void setUser(AuthorPOJO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PhotoPOJO{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", location=" + location +
                ", urls=" + urls +
                ", exif=" + exif +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", views='" + views + '\'' +
                ", downloads='" + downloads + '\'' +
                ", likes='" + likes + '\'' +
                ", user=" + user +
                '}';
    }
}
