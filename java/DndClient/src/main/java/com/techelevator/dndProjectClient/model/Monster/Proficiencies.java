package com.techelevator.dndProjectClient.model.Monster;

import java.util.ArrayList;
import java.util.List;

public class Proficiencies {

    public int value;

    private ArrayList<Proficiency> proficiencyList;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Proficiency> getProficiencies() {
        return proficiencyList;
    }

    public void setProficiencies(ArrayList<Proficiency> proficiencies) {
        this.proficiencyList = proficiencies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(value);
        sb.append( "  " + proficiencyList );

        return sb.toString();

    }
}
