package com.example.DndProject.Models.Monster;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Results {

    @JsonProperty("index")
    public String index;
    @JsonProperty("name")
    public String name;
    @JsonProperty("url")
    public String url;


    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}
