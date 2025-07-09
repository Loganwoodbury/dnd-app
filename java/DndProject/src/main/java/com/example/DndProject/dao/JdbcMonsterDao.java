package com.example.DndProject.dao;

import com.example.DndProject.Models.Monster.ArmorClass;
import com.example.DndProject.Models.Monster.Monster;
import com.example.DndProject.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JdbcMonsterDao implements MonsterDao {

    private final JdbcTemplate JDBCTEMPLATE;

    public JdbcMonsterDao(JdbcTemplate jdbctemplate) {
        JDBCTEMPLATE = jdbctemplate;
    }

    public List<Monster> getAllMonsters() {
        return List.of();
    }

    public Monster getMonsterByName(String name) {
        return null;
    }

    public Monster getMonsterById(int monsterId){
        Monster monster = null;

        String sql = "SELECT id, index, name, size, type, alignment, hit_points, hit_dice, hit_points_roll," +
                "strength, dexterity, constitution," +
                "intelligence, wisdom, charisma, languages, challenge_rating, proficiency_bonus, xp, url " +
                "FROM monster WHERE id = ?";

        try {
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, monsterId);
            if (results.next()){
                monster = mapRowToMonster(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }

        return monster;
    }

    public Monster createMonster(Monster monster) {
        Monster newMonster = null;

        String sql = "INSERT INTO monster (index, name, size, type, alignment, hit_points, hit_dice, hit_points_roll," +
                "strength, dexterity, constitution," +
                "intelligence, wisdom, charisma, languages, challenge_rating, proficiency_bonus, xp, url)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id";

        try {
            int newMonsterId = JDBCTEMPLATE.queryForObject(sql, int.class, monster.getIndex(), monster.getName(), monster.getSize(),
                    monster.getType(), monster.getAlignment(), monster.getHitPoints(), monster.getHitDice(), monster.getHitPointsRoll(),
                     monster.getStrength(), monster.getDexterity(), monster.getConstitution(), monster.getIntelligence(),
                    monster.getWisdom(), monster.getCharisma(), monster.getLanguages(), monster.getChallengeRating(), monster.getProficiencyBonus(),
                    monster.getXp(), monster.getUrl());

            newMonster = getMonsterById(newMonsterId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die){
            throw new DaoException("Data integrity violation", die);
        }

        return newMonster;
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
        monster.setStrength(rowSet.getInt("strength"));
        monster.setDexterity(rowSet.getInt("dexterity"));
        monster.setConstitution(rowSet.getInt("constitution"));
        monster.setIntelligence(rowSet.getInt("intelligence"));
        monster.setWisdom(rowSet.getInt("wisdom"));
        monster.setCharisma(rowSet.getInt("charisma"));
        monster.setLanguages(rowSet.getString("languages"));
        monster.setProficiencyBonus(rowSet.getInt("proficiency_bonus"));
        monster.setXp(rowSet.getInt("xp"));
        monster.setChallengeRating(rowSet.getDouble("challenge_rating"));
        monster.setUrl(rowSet.getString("url"));

        return monster;
    }

    private ArmorClass mapRowToArmorClass(SqlRowSet rowSet){
        ArmorClass armorClass = new ArmorClass();

        return armorClass;
    }
}
