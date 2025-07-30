package com.example.DndProject.Models.Monster;

import com.example.DndProject.Models.Action.Action;
import com.example.DndProject.Models.Condition.ConditionImmunity;
import com.example.DndProject.Models.Condition.ConditionType;
import com.example.DndProject.Models.Damage.DamageImmunity;
import com.example.DndProject.Models.Damage.DamageResistance;
import com.example.DndProject.Models.Damage.DamageVulnerability;
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
    public ArrayList<ArmorClass> armorClass = new ArrayList<>();
    @JsonProperty("hit_points")
    public int hitPoints;
    @JsonProperty("hit_dice")
    public String hitDice;
    @JsonProperty("hit_points_roll")
    public String hitPointsRoll;
    private Speed speed;
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    public int wisdom;
    public int charisma;
    public ArrayList<Proficiencies> proficiencies;
    @JsonProperty("damage_vulnerabilities")
    public ArrayList<String> rawDamageVulnerabilities;
    @JsonProperty("damage_resistances")
    public ArrayList<String> rawDamageResistances;
    @JsonProperty("damage_immunities")
    public ArrayList<String> rawDamageImmunities;
    private ArrayList<DamageResistance> damageResistances;
    private ArrayList<DamageImmunity> damageImmunities;
    private ArrayList<DamageVulnerability> damageVulnerabilities;
    @JsonProperty("condition_immunities")
    public ArrayList<ConditionType> rawConditionImmunities;
    private ArrayList<ConditionImmunity> conditionImmunities;
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

    public Monster() {};

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

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
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

    public ArrayList<ConditionType> getRawConditionImmunities() {
        return rawConditionImmunities;
    }

    public void setRawConditionImmunities(ArrayList<ConditionType> rawConditionImmunities) {
        this.rawConditionImmunities = rawConditionImmunities;
    }

    public ArrayList<ConditionImmunity> getConditionImmunities() {
        return conditionImmunities;
    }

    public void setConditionImmunities(ArrayList<ConditionImmunity> conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    public ArrayList<String> getRawDamageVulnerabilities() {
        return rawDamageVulnerabilities;
    }

    public void setRawDamageVulnerabilities(ArrayList<String> rawDamageVulnerabilities) {
        this.rawDamageVulnerabilities = rawDamageVulnerabilities;
    }

    public ArrayList<String> getRawDamageResistances() {
        return rawDamageResistances;
    }

    public void setRawDamageResistances(ArrayList<String> rawDamageResistances) {
        this.rawDamageResistances = rawDamageResistances;
    }

    public ArrayList<String> getRawDamageImmunities() {
        return rawDamageImmunities;
    }

    public void setRawDamageImmunities(ArrayList<String> rawDamageImmunities) {
        this.rawDamageImmunities = rawDamageImmunities;
    }

    public ArrayList<DamageResistance> getDamageResistances() {
        return damageResistances;
    }

    public void setDamageResistances(ArrayList<DamageResistance> damageResistances) {
        this.damageResistances = damageResistances;
    }

    public ArrayList<DamageImmunity> getDamageImmunities() {
        return damageImmunities;
    }

    public void setDamageImmunities(ArrayList<DamageImmunity> damageImmunities) {
        this.damageImmunities = damageImmunities;
    }

    public ArrayList<DamageVulnerability> getDamageVulnerabilities() {
        return damageVulnerabilities;
    }

    public void setDamageVulnerabilities(ArrayList<DamageVulnerability> damageVulnerabilities) {
        this.damageVulnerabilities = damageVulnerabilities;
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
