package com.example.DndProject.Models.Monster;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Damage {

    @JsonProperty("damageType")
    private ArrayList<DamageType> damagetype;
    @JsonProperty("damage_dice")
    private String damageDice;

    public ArrayList<DamageType> getDamageType() {
        return damagetype;
    }

    public void setDamageType(ArrayList<DamageType> damagetype) {
        this.damagetype = damagetype;
    }

    public String getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }
}
