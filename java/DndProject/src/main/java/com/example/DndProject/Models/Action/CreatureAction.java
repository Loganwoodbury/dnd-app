package com.example.DndProject.Models.Action;

import com.example.DndProject.Models.Monster.Monster;

public class CreatureAction {
    private Monster monster;
    private Action action;

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
