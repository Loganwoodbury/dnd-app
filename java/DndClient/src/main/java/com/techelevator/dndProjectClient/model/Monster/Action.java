package com.techelevator.dndProjectClient.model.Monster;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Action {
    private String name;
    private String desc;
    @JsonProperty("attack_bonus")
    private int attackBonus;
    @JsonProperty("damage")
    private ArrayList<Damage> damage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public ArrayList<Damage> getDamage() {
        return damage;
    }

    public void setDamage(ArrayList<Damage> damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        if(name != null){
            sb.append(name + "\n");
        }
        if(desc != null){
            sb.append(desc);
        }
        return sb.toString();
    }
}
