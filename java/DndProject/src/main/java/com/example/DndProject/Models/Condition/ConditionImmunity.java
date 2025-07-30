package com.example.DndProject.Models.Condition;

import com.example.DndProject.Models.Monster.Monster;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConditionImmunity {

    private int monsterId;
    private ConditionType conditionType;

    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }
}
