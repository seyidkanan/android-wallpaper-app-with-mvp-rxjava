package com.crocusoft.wallpaperappwithmvp.pojo.response;

import java.io.Serializable;

public class AuthorPOJO implements Serializable {

    private String name;
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
