package com.example.DndProject.Models.Damage;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Damage {

    @JsonProperty("damage_type")
    private DamageType damagetype;
    @JsonProperty("damage_dice")
    private String damageDice;

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
}
