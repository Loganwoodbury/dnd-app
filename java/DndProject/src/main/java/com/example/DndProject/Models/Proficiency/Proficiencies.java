package com.example.DndProject.Models.Proficiency;

import com.example.DndProject.Models.Monster.Monster;

public class Proficiencies {

    private Monster monster;
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
