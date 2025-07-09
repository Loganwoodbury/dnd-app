package com.example.DndProject.Models.Proficiency;

import com.example.DndProject.Models.Monster.Monster;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Proficiencies {

    private Monster monster;
    @JsonProperty("proficiency")
    private ProficiencyType proficiencyType;
    private int value;

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public ProficiencyType getProficiencyType() {
        return proficiencyType;
    }

    public void setProficiencyType(ProficiencyType proficiencyType) {
        this.proficiencyType = proficiencyType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
