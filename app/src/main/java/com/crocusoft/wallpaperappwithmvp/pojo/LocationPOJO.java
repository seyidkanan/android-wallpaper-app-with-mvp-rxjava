package com.crocusoft.wallpaperappwithmvp.pojo;

import java.io.Serializable;

public class LocationPOJO implements Serializable {

    private String city;
    private String country;
    private String title;
    private String name;
    private LocationPositionPOJO position;

    public LocationPositionPOJO getPosition() {
        return position;
    }

    public void setPosition(LocationPositionPOJO position) {
        this.position = position;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
