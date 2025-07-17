package com.example.DndProject.Models.Damage;

import com.example.DndProject.Models.Monster.Monster;

public class DamageImmunity {

    private int id;
    private int monsterId;
    private ImmVulnResType immunityType;
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }

    public ImmVulnResType getImmunityType() {
        return immunityType;
    }

    public void setImmunityType(ImmVulnResType immunityType) {
        this.immunityType = immunityType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
