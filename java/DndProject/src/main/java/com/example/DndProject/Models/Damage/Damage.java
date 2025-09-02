package com.example.DndProject.Models.Damage;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Damage {

    private int id;
    @JsonProperty("damage_type")
    private DamageType damagetype;
    @JsonProperty("damage_dice")
    private String damageDice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DamageType getDamagetype() {
        return damagetype;
    }

    public void setDamagetype(DamageType damagetype) {
        this.damagetype = damagetype;
    }

    public String getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public Damage(int id, DamageType damagetype, String damageDice) {
        this.id = id;
        this.damagetype = damagetype;
        this.damageDice = damageDice;
    }
}
