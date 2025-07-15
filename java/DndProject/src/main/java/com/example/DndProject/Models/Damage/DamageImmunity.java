package com.example.DndProject.Models.Damage;

import com.example.DndProject.Models.Monster.Monster;

public class DamageImmunity {
    private int monsterId;
    private DamageType damageType;
    private String notes;

    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
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
