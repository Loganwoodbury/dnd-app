package com.example.DndProject.Models.Damage;

import com.example.DndProject.Models.Monster.Monster;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DamageResistance {
    private int id;
    private int monsterId;
    private ImmVulnResType resistanceType;
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

    public ImmVulnResType getResistanceType() {
        return resistanceType;
    }

    public void setResistanceType(ImmVulnResType resistanceType) {
        this.resistanceType = resistanceType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
