package com.example.DndProject.dao;

import com.example.DndProject.Models.Monster.ArmorClass;
import com.example.DndProject.Models.Monster.Monster;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class JdbcMonsterDao implements MonsterDao {

    private final JdbcTemplate JDBCTEMPLATE;

    public JdbcMonsterDao(JdbcTemplate jdbctemplate) {
        JDBCTEMPLATE = jdbctemplate;
    }

    @Override
    public List<Monster> getAllMonsters() {
        return List.of();
    }

    @Override
    public Monster getMonsterByName(String name) {
        return null;
    }

    @Override
    public Monster createMonster(Monster monster) {
        return null;
    }

    private Monster mapRowToMonster(SqlRowSet rowSet){
        Monster monster = new Monster();

        monster.setId(rowSet.getInt("id"));
        monster.setIndex(rowSet.getString("index"));
        monster.setName(rowSet.getString("name"));
        monster.setSize(rowSet.getString("size"));
        monster.setType(rowSet.getString("type"));
        monster.setAlignment(rowSet.getString("alignment"));
        monster.setHitPoints(rowSet.getInt("hit_points"));
        monster.setHitDice(rowSet.getString("hit_dice"));
        monster.setHitPointsRoll(rowSet.getString("hit_points_roll"));
        monster.setSpeedWalk(rowSet.getString("speed_walk"));
        monster.setSpeedFly(rowSet.getString("speed_fly"));
        monster.setSpeedHover(rowSet.getString("speed_hover"));
        monster.setSpeedBurrow(rowSet.getString("speed_burrow"));
        monster.setSpeedSwim(rowSet.getString("speed_swim"));
        monster.setSpeedClimb(rowSet.getString("speed_climb"));
        monster.setStrength(rowSet.getInt("strength"));
        monster.setDexterity(rowSet.getInt("dexterity"));
        monster.setConstitution(rowSet.getInt("constitution"));
        monster.setIntelligence(rowSet.getInt("intelligence"));
        monster.setWisdom(rowSet.getInt("wisdom"));
        monster.setCharisma(rowSet.getInt("charisma"));
        monster.setLanguages(rowSet.getString("languages"));
        monster.setProficiencyBonus(rowSet.getInt("proficiency_bonus"));
        monster.setXp(rowSet.getInt("xp"));
        monster.setUrl(rowSet.getString("url"));

        return monster;
    }

    private ArmorClass mapRowToArmorClass(SqlRowSet rowSet){
        ArmorClass armorClass = new ArmorClass();

        return armorClass;
    }
}
