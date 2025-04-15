package com.example.DndProject.Models.Monster;

import java.util.List;

public class Proficiencies {

    public int value;

    private List<Proficiency> proficiencies;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Proficiency> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(List<Proficiency> proficiencies) {
        this.proficiencies = proficiencies;
    }
}
