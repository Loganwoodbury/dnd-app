package com.example.DndProject.Models.Action;

import com.example.DndProject.Models.DC.ActionDc;
import com.example.DndProject.Models.DC.DcType;

public class LegendaryAction {
    private int id;
    private String name;
    private String description;
    private int damage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ActionDc getActionDc() {
        return actionDc;
    }

    public void setActionDc(ActionDc actionDc) {
        this.actionDc = actionDc;
    }
}
