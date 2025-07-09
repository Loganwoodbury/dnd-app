package com.example.DndProject.Models.Action;

import com.example.DndProject.Models.DC.ActionDc;
import com.example.DndProject.Models.Damage.Damage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Action {

    private int id;
    private String name;
    private String desc;
    @JsonProperty("attack_bonus")
    private int attackBonus;
    @JsonProperty("multiattack_type")
    private String multiAttackType;
    private String usageType;
    private int usageTimes;
    @JsonProperty("damage")
    private List<Damage> damage;
    @JsonProperty("dc")
    private ActionDc actionDc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public String getMultiAttackType() {
        return multiAttackType;
    }

    public void setMultiAttackType(String multiAttackType) {
        this.multiAttackType = multiAttackType;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public int getUsageTimes() {
        return usageTimes;
    }

    public void setUsageTimes(int usageTimes) {
        this.usageTimes = usageTimes;
    }

    public List<Damage> getDamage() {
        return damage;
    }

    public void setDamage(List<Damage> damage) {
        this.damage = damage;
    }

    public ActionDc getActionDc() {
        return actionDc;
    }

    public void setActionDc(ActionDc actionDc) {
        this.actionDc = actionDc;
    }
}
