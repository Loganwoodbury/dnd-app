package com.techelevator.dndProjectClient.model.Monster;

public class SpecialAbility {

    private String name;
    private String desc;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        if(name != null){
            sb.append(name + "\n\n");
        }
        if(desc != null){
            sb.append(desc + "\n\n");
        }
        return sb.toString();
    }
}
