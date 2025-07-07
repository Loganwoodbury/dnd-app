package com.example.DndProject.Models.Monster;

public class MonsterAbility {
    private Monster monster;
    private SpecialAbility specialAbility;

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public SpecialAbility getSpecialAbility() {
        return specialAbility;
    }

    public void setSpecialAbility(SpecialAbility specialAbility) {
        this.specialAbility = specialAbility;
    }
}
