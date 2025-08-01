package com.example.DndProject.Models.Proficiency;

public class ProficiencyType {

    private int id;
    private String index;
    private String name;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

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

    @Override
    public String toString() {
        return "Proficiency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
