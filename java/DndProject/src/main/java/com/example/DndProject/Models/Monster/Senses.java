package com.example.DndProject.Models.Monster;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Senses {

    private String darkvision;
    private String blindsight;
    private String tremorsense;
    private String truesight;
    @JsonProperty("passive_perception")
    private int passivePerception;

    public String getDarkvision() {
        return darkvision;
    }

    public void setDarkvision(String darkvision) {
        this.darkvision = darkvision;
    }

    public String getBlindsight() {
        return blindsight;
    }

    public void setBlindsight(String blindsight) {
        this.blindsight = blindsight;
    }

    public String getTremorsense() {
        return tremorsense;
    }

    public void setTremorsense(String tremorsense) {
        this.tremorsense = tremorsense;
    }

    public String getTruesight() {
        return truesight;
    }

    public void setTruesight(String truesight) {
        this.truesight = truesight;
    }

    public int getPassivePerception() {
        return passivePerception;
    }

    public void setPassivePerception(int passivePerception) {
        this.passivePerception = passivePerception;
    }
}
