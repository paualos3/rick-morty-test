package com.example.paualos3.rick_morty_test.Models;

import java.io.Serializable;

public class LocationModel implements Serializable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String name;
    private String url;

}
