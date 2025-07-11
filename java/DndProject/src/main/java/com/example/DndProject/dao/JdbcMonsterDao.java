package com.example.DndProject.dao;

import com.example.DndProject.Models.Damage.DamageType;
import com.example.DndProject.Models.Monster.ArmorClass;
import com.example.DndProject.Models.Monster.Monster;
import com.example.DndProject.Models.Proficiency.Proficiencies;
import com.example.DndProject.Models.Proficiency.ProficiencyType;
import com.example.DndProject.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        Monster monster = new Monster();

        //get monster
        String sql = "SELECT * FROM monster WHERE name ILIKE ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, name);
            if(results.next()){
                monster = mapRowToMonster(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        //get armor for monster
        ArrayList<ArmorClass> armorClass = getArmorClass(monster.getId());
        monster.setArmorClass(armorClass);

        //Get proficiencies
        ArrayList<Proficiencies> proficiencies = getCreatureProficiencies(monster.getId());
        monster.setProficiencies(proficiencies);

        return monster;
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
    @Transactional
    public Monster createMonster(Monster monster) {

        System.out.println("starting createMonster for: {}"+ monster.getName());
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

            //create armor_class entry
            createArmorClass(newMonsterId, monster.getArmorClass());

            //create profType
            for(Proficiencies p : monster.getProficiencies()){
                createProficiencyType(p.getProficiencyType());
            };

            //create prof junction
            createMonsterProficiencies(monster.getProficiencies(), newMonsterId);



        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die){
            throw new DaoException("Data integrity violation", die);
        }
        return newMonster;


    }

    public void createArmorClass(int monsterId, List<ArmorClass> armorClasses){

        String sql = "INSERT INTO armor_class (creature_id, type, value, equipment_desc, spell_desc) " +
                "VALUES (?, ?, ?, ?, ?)";

        try{
            if(armorClasses != null && !armorClasses.isEmpty()){
                System.out.println("found armor classes in monster preparing batch insert");
                List<Object[]> batchArgs = new ArrayList<>();
                for(ArmorClass ac : armorClasses){
                    batchArgs.add(new Object[]{
                            monsterId,
                            ac.getType(),
                            ac.getValue(),
                            ac.getEquipmentDescription(),
                            ac.getSpellDescription()
                    });
                }//end for loop
                JDBCTEMPLATE.batchUpdate(sql, batchArgs);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die){
            throw new DaoException("Data integrity violation", die);
        }
    }

    public ArrayList<ArmorClass> getArmorClass(int monsterId){
        ArrayList<ArmorClass> armorClass = new ArrayList<>();

        String sql = "SELECT * FROM armor_class WHERE creature_id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, monsterId);
            if (results.next()){
                armorClass.add(mapRowToArmorClass(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return armorClass;
    }

    public void createProficiencyType(ProficiencyType proficiencyType){

        ProficiencyType existingType = getProficiencyTypeByName(proficiencyType.getName());

        String sql = "INSERT INTO proficiency_type (index, name, url) VALUES (?, ?, ?) RETURNING id";

        if(existingType.getName() == null) {
            try {
                int newPofTypeId = JDBCTEMPLATE.queryForObject(sql, int.class, proficiencyType.getIndex(), proficiencyType.getName(),
                        proficiencyType.getUrl());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException die) {
                throw new DaoException("Data integrity violation", die);
            }
        }

    }

    public ProficiencyType getProficiencyTypeByName(String profTypeName){
        ProficiencyType profType = new ProficiencyType();
        String sql = "SELECT * FROM proficiency_type WHERE name ILIKE ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, profTypeName);
            if (results.next()) {
                profType = mapRowToProficiencyType(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }

        return profType;
    }

    private ProficiencyType getProficiencyTypeById(int profTypeId){
        ProficiencyType profType = new ProficiencyType();
        String sql = "SELECT * FROM proficiency_type WHERE id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, profTypeId);
            if (results.next()) {
                profType = mapRowToProficiencyType(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }

        return profType;

    }

    public Proficiencies createMonsterProficiencies(List<Proficiencies> proficiency, int monsterId){
        Proficiencies newProficiency = new Proficiencies();

        String sql = "INSERT INTO proficiency_junction (creature_id, proficiency_type_id, value) " +
                "VALUES (?, ?, ?) RETURNING creature_id";

        for(Proficiencies p : proficiency){
            int pTypeId = getProficiencyTypeByName(p.getProficiencyType().getName()).getId();
            try{
                int newJunctionKey = JDBCTEMPLATE.queryForObject(sql, int.class, monsterId, pTypeId, p.getValue());
            }catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException die) {
                throw new DaoException("Data integrity violation", die);
            }
        }

        return newProficiency;
    }

    private ArrayList<Proficiencies> getCreatureProficiencies(int monsterId){
        ArrayList<Proficiencies> proficiencies = new ArrayList<>();

        String sql = "SELECT * from proficiency_junction WHERE creature_id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, monsterId);
            while(results.next()){
                proficiencies.add(mapRowToProficiencies(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return proficiencies;
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

        armorClass.setId(rowSet.getInt("id"));
        armorClass.setMonsterId(rowSet.getInt("creature_id"));
        armorClass.setType(rowSet.getString("type"));
        armorClass.setValue(rowSet.getInt("value"));
        armorClass.setEquipmentDescription(rowSet.getString("equipment_desc"));
        armorClass.setSpellDescription(rowSet.getString("spell_desc"));

        return armorClass;
    }

    private ProficiencyType mapRowToProficiencyType(SqlRowSet rowSet){
        ProficiencyType proficiencyType = new ProficiencyType();

        proficiencyType.setId(rowSet.getInt("id"));
        proficiencyType.setIndex(rowSet.getString("index"));
        proficiencyType.setName(rowSet.getString("name"));
        proficiencyType.setUrl(rowSet.getString("url"));

        return proficiencyType;
    }

    private Proficiencies mapRowToProficiencies(SqlRowSet rowSet){
        Proficiencies proficiencies = new Proficiencies();

        proficiencies.setMonsterId(rowSet.getInt("creature_id"));
        proficiencies.setValue(rowSet.getInt("value"));
        proficiencies.setProficiencyType(getProficiencyTypeById(rowSet.getInt("proficiency_type_id")));

        return proficiencies;
    }
}
