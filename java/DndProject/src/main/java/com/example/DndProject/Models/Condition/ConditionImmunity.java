package com.example.DndProject.Models.Condition;

import com.example.DndProject.Models.Monster.Monster;

public class ConditionImmunity {

    private Monster monster;
    private ConditionType conditionType;

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }
}
