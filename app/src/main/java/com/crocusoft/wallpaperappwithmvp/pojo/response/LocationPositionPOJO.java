package com.crocusoft.wallpaperappwithmvp.pojo.response;

import java.io.Serializable;

public class LocationPositionPOJO implements Serializable {

    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}