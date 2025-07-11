package com.example.DndProject.Models.Proficiency;

import com.example.DndProject.Models.Monster.Monster;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Proficiencies {

    private int monsterId;
    @JsonProperty("proficiency")
    private ProficiencyType proficiencyType;
    private int value;

    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
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
