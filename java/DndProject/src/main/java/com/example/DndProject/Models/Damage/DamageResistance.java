package com.example.DndProject.Models.Damage;

import com.example.DndProject.Models.Monster.Monster;

public class DamageResistance {
    private Monster monster;
    private DamageType damageType;
    private String notes;

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
