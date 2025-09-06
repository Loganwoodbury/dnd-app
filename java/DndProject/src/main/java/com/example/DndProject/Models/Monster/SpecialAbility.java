package com.example.DndProject.Models.Monster;

import com.example.DndProject.Models.Action.Usage;
import com.example.DndProject.Models.DC.ActionDc;
import com.example.DndProject.Models.Damage.Damage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpecialAbility {

    private int id;
    private int index;
    private String name;
    private String desc;
    private Usage usage;
    @JsonProperty("dc")
    private ActionDc actionDc;
    @JsonProperty("damage")
    private List<Damage> damage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

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

    public ActionDc getActionDc() {
        return actionDc;
    }

    public void setActionDc(ActionDc actionDc) {
        this.actionDc = actionDc;
    }

    public List<Damage> getDamage() {
        return damage;
    }

    public void setDamage(List<Damage> damage) {
        this.damage = damage;
    }
}
