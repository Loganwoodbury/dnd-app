package com.example.DndProject.Models.Monster;

import com.example.DndProject.Models.Action.Action;
import com.example.DndProject.Models.Condition.ConditionType;
import com.example.DndProject.Models.Proficiency.Proficiencies;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Monster {
    public String index;
    public String name;
    public String size;
    public String type;
    public int id;
    public String alignment;
    @JsonProperty("armor_class")
    public ArrayList<ArmorClass> armorClass;
    @JsonProperty("hit_points")
    public int hitPoints;
    @JsonProperty("hit_dice")
    public String hitDice;
    @JsonProperty("hit_points_roll")
    public String hitPointsRoll;
    public String speedWalk;
    public String speedFly;
    public String speedHover;
    public String speedBurrow;
    public String speedSwim;
    private String speedClimb;
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    public int wisdom;
    public int charisma;
    public ArrayList<Proficiencies> proficiencies;
    @JsonProperty("damage_vulnerabilities")
    public ArrayList<String> damageVulnerabilities;
    @JsonProperty("damage_resistances")
    public ArrayList<String> damageResistances;
    @JsonProperty("damage_immunities")
    public ArrayList<String> damageImmunities;
    @JsonProperty("condition_immunities")
    public ArrayList<ConditionType> conditionImmunities;
    public Senses senses;
    public String languages;
    @JsonProperty("challenge_rating")
    public double challengeRating;
    @JsonProperty("proficiency_bonus")
    public int proficiencyBonus;
    public int xp;
    public ArrayList<SpecialAbility> special_abilities;
    public ArrayList<Action> actions;
    public String url;
    public ArrayList<Object> legendary_actions;
    public String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getSpeedWalk() {
        return speedWalk;
    }

    public void setSpeedWalk(String speedWalk) {
        this.speedWalk = speedWalk;
    }

    public String getSpeedFly() {
        return speedFly;
    }

    public void setSpeedFly(String speedFly) {
        this.speedFly = speedFly;
    }

    public String getSpeedHover() {
        return speedHover;
    }

    public void setSpeedHover(String speedHover) {
        this.speedHover = speedHover;
    }

    public String getSpeedBurrow() {
        return speedBurrow;
    }

    public void setSpeedBurrow(String speedBurrow) {
        this.speedBurrow = speedBurrow;
    }

    public String getSpeedSwim() {
        return speedSwim;
    }

    public void setSpeedSwim(String speedSwim) {
        this.speedSwim = speedSwim;
    }

    public String getSpeedClimb() {
        return speedClimb;
    }

    public void setSpeedClimb(String speedClimb) {
        this.speedClimb = speedClimb;
    }

    public ArrayList<ArmorClass> getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(ArrayList<ArmorClass> armorClass) {
        this.armorClass = armorClass;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public String getHitDice() {
        return hitDice;
    }

    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }

    public String getHitPointsRoll() {
        return hitPointsRoll;
    }

    public void setHitPointsRoll(String hitPointsRoll) {
        this.hitPointsRoll = hitPointsRoll;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public ArrayList<Proficiencies> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(ArrayList<Proficiencies> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public ArrayList<String> getDamageVulnerabilities() {
        return damageVulnerabilities;
    }

    public void setDamageVulnerabilities(ArrayList<String> damageVulnerabilities) {
        this.damageVulnerabilities = damageVulnerabilities;
    }

    public ArrayList<String> getDamageResistances() {
        return damageResistances;
    }

    public void setDamageResistances(ArrayList<String> damageResistances) {
        this.damageResistances = damageResistances;
    }

    public ArrayList<String> getDamageImmunities() {
        return damageImmunities;
    }

    public void setDamageImmunities(ArrayList<String> damageImmunities) {
        this.damageImmunities = damageImmunities;
    }

    public ArrayList<ConditionType> getConditionImmunities() {
        return conditionImmunities;
    }

    public void setConditionImmunities(ArrayList<ConditionType> conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    public Senses getSenses() {
        return senses;
    }

    public void setSenses(Senses senses) {
        this.senses = senses;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public double getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(double challengeRating) {
        this.challengeRating = challengeRating;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public ArrayList<SpecialAbility> getSpecial_abilities() {
        return special_abilities;
    }

    public void setSpecial_abilities(ArrayList<SpecialAbility> special_abilities) {
        this.special_abilities = special_abilities;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Object> getLegendary_actions() {
        return legendary_actions;
    }

    public void setLegendary_actions(ArrayList<Object> legendary_actions) {
        this.legendary_actions = legendary_actions;
    }
}
