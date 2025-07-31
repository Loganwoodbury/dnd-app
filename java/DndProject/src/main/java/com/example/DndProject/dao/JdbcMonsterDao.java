package com.example.DndProject.dao;

import com.example.DndProject.Models.Condition.ConditionImmunity;
import com.example.DndProject.Models.Condition.ConditionType;
import com.example.DndProject.Models.Damage.*;
import com.example.DndProject.Models.Monster.ArmorClass;
import com.example.DndProject.Models.Monster.Monster;
import com.example.DndProject.Models.Monster.Senses;
import com.example.DndProject.Models.Monster.Speed;
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

        //Get damage Resistances
        ArrayList<DamageResistance> damageResistances = getDamageResistances(monster.getId());
        monster.setDamageResistances(damageResistances);

        //Get damage Immunities
        monster.setDamageImmunities(getDamageImmunity(monster.getId()));

        //Get damage Vulnerabilities
        monster.setDamageVulnerabilities(getDamageVulnerability(monster.getId()));

        //Get condition Immunities
        monster.setConditionImmunities(getCreatureConditionImmunity(monster.getId()));

        //Get speed
        monster.setSpeed(getCreatureSpeed(monster.getId()));

        //Get senses
        monster.setSenses(getCreatureSenses(monster.getId()));

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

            //create Damage Resistance
            createImmVulnResType(monster.getRawDamageResistances());
            createDamageResistance(monster.getRawDamageResistances(), newMonsterId);

            //create Damage immunity
            createImmVulnResType(monster.getRawDamageImmunities());
            createDamageImmunity(monster.getRawDamageImmunities(),  newMonsterId);

            //create Damage Vulnerability
            createImmVulnResType(monster.getRawDamageVulnerabilities());
            createDamageVulnerablity(monster.getRawDamageVulnerabilities(), newMonsterId);

            //create ConditionType
            createConditionType(monster.getRawConditionImmunities());
            //create MonsterCondition
            createMonsterConditionImmunity(monster.getRawConditionImmunities(), newMonsterId);
            //create speed
            createCreatureSpeed(monster.getSpeed(), newMonsterId);
            //create senses
            createCreatureSenses(monster.getSenses(), newMonsterId);



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

    private DamageType getDamageTypeByName(String damageTypeName){
        DamageType damageType = new DamageType();

        String sql = "SELECT * FROM damage_type WHERE name ILIKE ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, damageTypeName);
            if (results.next()){
                damageType = mapRowToDamageType(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return damageType;
    }

    private DamageType getDamageTypeById(int damageTypeId){
        DamageType damageType = new DamageType();

        String sql = "SELECT * FROM damage_type WHERE id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, damageTypeId);
            if (results.next()){
                damageType = mapRowToDamageType(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return damageType;
    }


    private DamageType createDamageType(DamageType damageType){
        DamageType newDamageType = new DamageType();
        DamageType existingDamageType = getDamageTypeByName(damageType.getName());

        String sql = "INSERT INTO damage_type (index, name, url) VALUES (?, ?, ?) RETURNING name";
        if(existingDamageType == null){
            try{
                String newDamageTypename = JDBCTEMPLATE.queryForObject(sql, String.class, damageType.getIndex(), damageType.getName(),
                        damageType.getUrl());
                newDamageType = getDamageTypeByName(newDamageTypename);
            }catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException die) {
                throw new DaoException("Data integrity violation", die);
            }
        }
        return newDamageType;
    }

    private void createImmVulnResType(ArrayList<String> typeName){

        if(typeName != null && !typeName.isEmpty()) {
            System.out.println("found immrestypeList");
            for (String type : typeName) {
                System.out.println(type);
                ImmVulnResType existingType = getImVulnResTypeByName(type);

                String sql = "INSERT INTO res_imm_vuln_type (damage_type) VALUES (?) RETURNING id";

                if (existingType == null) {
                    System.out.println("creating restype");
                    try {
                        int newTypeId = JDBCTEMPLATE.queryForObject(sql, int.class, type);
                    } catch (CannotGetJdbcConnectionException e) {
                        throw new DaoException("Unable to connect to server or database", e);
                    } catch (DataIntegrityViolationException die) {
                        throw new DaoException("Data integrity violation", die);
                    }
                }
            }
        }
    }

    private ImmVulnResType getImVulnResTypeByName(String typeName){
        ImmVulnResType immVulnResType = null;

        String sql = "SELECT * FROM res_imm_vuln_type WHERE damage_type ILIKE ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, typeName);
            if (results.next()) {
                immVulnResType = mapRowToImmVulnResType(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }

        return immVulnResType;
    }

    private ImmVulnResType getImmVulnResTypeById(int typeId){
        ImmVulnResType resType = new ImmVulnResType();
        String sql = "SELECT * FROM res_imm_vuln_type WHERE id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, typeId);
            if (results.next()) {
                resType = mapRowToImmVulnResType(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }

        return resType;
    }

    private void createDamageResistance(ArrayList<String> resTypes, int monsterId){
        DamageResistance newDamageResistance = new DamageResistance();

        String sql = "INSERT INTO monster_damage_resistance (creature_id, damage_type_id) \n" +
                "VALUES (?, (SELECT id FROM res_imm_vuln_type WHERE damage_type ILIKE ?)) RETURNING id";

        if(resTypes!= null && !resTypes.isEmpty()) {
            System.out.println("Found resistances");
            for (String damRes : resTypes) {
                System.out.println("inserting into monster resistance: " + damRes);
                try {
                    int newJunctionKey = JDBCTEMPLATE.queryForObject(sql, int.class, monsterId, damRes);
                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                } catch (DataIntegrityViolationException die) {
                    throw new DaoException("Data integrity violation", die);
                }
            }
        }
    }

    private ArrayList<DamageResistance> getDamageResistances(int monsterId){
        ArrayList<DamageResistance> damageResistances = new ArrayList<>();

        String sql = "SELECT * FROM monster_damage_resistance WHERE creature_id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, monsterId);
            while(results.next()){
                damageResistances.add(mapRowToDamageResistance(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return damageResistances;
    }

    private void createDamageImmunity(ArrayList<String> immunities, int monsterId){

        String sql = "INSERT INTO monster_damage_immunity (creature_id, damage_type_id) \n" +
                "VALUES (?, (SELECT id FROM res_imm_vuln_type WHERE damage_type ILIKE ?)) RETURNING id";

        if(immunities!= null && !immunities.isEmpty()) {
            System.out.println("Found immunities");
            for (String damImm : immunities) {
                System.out.println("inserting into monster immunity: " + damImm);
                try {
                    int newJunctionKey = JDBCTEMPLATE.queryForObject(sql, int.class, monsterId, damImm);
                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                } catch (DataIntegrityViolationException die) {
                    throw new DaoException("Data integrity violation", die);
                }
            }
        }
    }

    private ArrayList<DamageImmunity> getDamageImmunity(int monsterId){
        ArrayList<DamageImmunity> damageImmunities = new ArrayList<>();

        String sql = "SELECT * FROM monster_damage_immunity WHERE creature_id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, monsterId);
            while(results.next()){
                damageImmunities.add(mapRowToDamageImmunity(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return damageImmunities;
    }

    private void createDamageVulnerablity(ArrayList<String> vulnerabilities, int monsterId){

        String sql = "INSERT INTO monster_damage_vulnerability (creature_id, damage_type_id) \n" +
                "VALUES (?, (SELECT id FROM res_imm_vuln_type WHERE damage_type ILIKE ?)) RETURNING id";

        if(vulnerabilities!= null && !vulnerabilities.isEmpty()) {
            System.out.println("Found vulnerabilites");
            for (String damVuln : vulnerabilities) {
                System.out.println("inserting into monster vulnerability: " + damVuln);
                try {
                    int newJunctionKey = JDBCTEMPLATE.queryForObject(sql, int.class, monsterId, damVuln);
                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                } catch (DataIntegrityViolationException die) {
                    throw new DaoException("Data integrity violation", die);
                }
            }
        }
    }

    private ArrayList<DamageVulnerability> getDamageVulnerability(int monsterId){
        ArrayList<DamageVulnerability> damageVulnerabilities = new ArrayList<>();

        String sql = "SELECT * FROM monster_damage_vulnerability WHERE creature_id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, monsterId);
            while(results.next()){
                damageVulnerabilities.add(mapRowToDamageVulnerability(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return damageVulnerabilities;

    }

    private void createConditionType(ArrayList<ConditionType> conditionTypes){
        System.out.println(conditionTypes);
        String sql = "INSERT INTO condition_type(index, name, url) " +
                "VALUES(?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        if(conditionTypes != null && !conditionTypes.isEmpty()) {
            System.out.println("found conditions");
            for (ConditionType condition : conditionTypes) {
                ConditionType existingType = getConditionTypeByName(condition.getName());
                if(existingType.getName() == null) {
                    System.out.println("inserting into conditiontype : " + condition.getName());
                    batchArgs.add(new Object[]{
                            condition.getIndex(),
                            condition.getName(),
                            condition.getUrl()
                    });
                }
            }
            JDBCTEMPLATE.batchUpdate(sql, batchArgs);
        }
    }

    private ConditionType getConditionTypeById(int typeId){

        ConditionType condition = new ConditionType();
        String sql = "SELECT * FROM condition_type WHERE id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, typeId);
            if(results.next()){
                condition = mapRowToConditionType(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return condition;
    }

    private ConditionType getConditionTypeByName(String name){
        ConditionType conditionType = new ConditionType();

        String sql = "SELECT * FROM condition_type WHERE name ILIKE ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, name);
            if(results.next()){
                conditionType = mapRowToConditionType(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return conditionType;

    }

    private void createMonsterConditionImmunity(ArrayList<ConditionType> conditions, int monsterId){

        String sql = "INSERT INTO monster_condition_immunity(creature_id, condition_type_id) " +
                "VALUES (?, (SELECT id FROM condition_type WHERE name ILIKE ?))";

        List<Object[]> batchArgs = new ArrayList<>();
        if(conditions != null && !conditions.isEmpty()) {
            for (ConditionType condition : conditions) {
                    System.out.println("creating monster condition : " + condition.getName());
                    batchArgs.add(new Object[]{
                            monsterId,
                            condition.getName()
                    });
                }
            JDBCTEMPLATE.batchUpdate(sql, batchArgs);
        }
    }

    private ArrayList<ConditionImmunity> getCreatureConditionImmunity(int monsterId){
        ArrayList<ConditionImmunity> conditionImmunities = new ArrayList<>();

        String sql = "SELECT * FROM monster_condition_immunity WHERE creature_id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, monsterId);
            while(results.next()){
                conditionImmunities.add(mapRowToConditionImmunity(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return conditionImmunities;
    }

    private void createCreatureSpeed(Speed speed, int monsterId){
        System.out.println(speed);

        String sql = "INSERT INTO speed (creature_id, walk, fly, hover, burrow, climb, swim) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try {
            int newJunctionKey = JDBCTEMPLATE.queryForObject(sql, int.class, monsterId, speed.getWalk(),speed.getFly(),
                speed.getHover(), speed.getBurrow(), speed.getClimb(), speed.getSwim());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
    }

    private Speed getCreatureSpeed(int monsterId){
        Speed speed = new Speed();
        String sql = "SELECT * FROM speed WHERE creature_id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, monsterId);
            if(results.next()){
                speed = mapRowToSpeed(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return speed;
    }

    private void createCreatureSenses(Senses senses, int monsterId){

        String sql = "INSERT INTO senses (creature_id, darkvision, blindsight, tremorsense, truesight, passive_perception) " +
                "VALUES ( ?, ?, ?, ?, ?, ?) RETURNING id";

        try{
            int newSensesId = JDBCTEMPLATE.queryForObject(sql, int.class, monsterId, senses.getDarkvision(), senses.getBlindsight(),
                    senses.getTremorsense(), senses.getTruesight(), senses.getPassivePerception());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
    }

    private Senses getCreatureSenses(int monsterId){
        Senses senses = new Senses();

        String sql = "SELECT * FROM senses WHERE creature_id = ?";

        try{
            SqlRowSet results = JDBCTEMPLATE.queryForRowSet(sql, monsterId);
            if(results.next()){
                senses = mapRowToSenses(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException die) {
            throw new DaoException("Data integrity violation", die);
        }
        return senses;
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

    private DamageType mapRowToDamageType(SqlRowSet rowSet){
        DamageType damageType = new DamageType();

        damageType.setId(rowSet.getInt("id"));
        damageType.setIndex(rowSet.getString("index"));
        damageType.setName(rowSet.getString("name"));
        damageType.setUrl(rowSet.getString("url"));

        return damageType;
    }

    private ImmVulnResType mapRowToImmVulnResType(SqlRowSet rowSet){
        ImmVulnResType immVulnResType = new ImmVulnResType();

        immVulnResType.setId(rowSet.getInt("id"));
        immVulnResType.setDamageType(rowSet.getString("damage_type"));

        return immVulnResType;
    }

    private DamageResistance mapRowToDamageResistance(SqlRowSet rowSet){
        DamageResistance damageResistance = new DamageResistance();

        damageResistance.setId(rowSet.getInt("id"));
        damageResistance.setMonsterId(rowSet.getInt("creature_id"));
        damageResistance.setResistanceType(getImmVulnResTypeById(rowSet.getInt("damage_type_id")));
        damageResistance.setNotes(rowSet.getString("notes"));

        return damageResistance;
    }

    private DamageImmunity mapRowToDamageImmunity(SqlRowSet rowSet){
        DamageImmunity damageImmunity = new DamageImmunity();

        damageImmunity.setId(rowSet.getInt("id"));
        damageImmunity.setMonsterId(rowSet.getInt("creature_id"));
        damageImmunity.setNotes(rowSet.getString("notes"));
        damageImmunity.setImmunityType(getImmVulnResTypeById(rowSet.getInt("damage_type_id")));

        return damageImmunity;
    }

    private DamageVulnerability mapRowToDamageVulnerability(SqlRowSet rowSet){
        DamageVulnerability damageVulnerability = new DamageVulnerability();

        damageVulnerability.setId(rowSet.getInt("id"));
        damageVulnerability.setMonsterId(rowSet.getInt("creature_id"));
        damageVulnerability.setVulnType(getImmVulnResTypeById(rowSet.getInt("damage_type_id")));
        damageVulnerability.setNotes(rowSet.getString("notes"));

        return damageVulnerability;
    }

    private ConditionType mapRowToConditionType(SqlRowSet rowSet){
        ConditionType condition = new ConditionType();

        condition.setId(rowSet.getInt("id"));
        condition.setIndex(rowSet.getString("index"));
        condition.setName(rowSet.getString("name"));
        condition.setUrl(rowSet.getString("url"));

        return condition;
    }

    private ConditionImmunity mapRowToConditionImmunity(SqlRowSet rowSet){
        ConditionImmunity conditionImmunity = new ConditionImmunity();

        conditionImmunity.setConditionType(getConditionTypeById(rowSet.getInt("condition_type_id")));
        conditionImmunity.setMonsterId(rowSet.getInt("creature_id"));

        return conditionImmunity;
    }

    private Speed mapRowToSpeed(SqlRowSet rowSet){
        Speed speed = new Speed();

        speed.setWalk(rowSet.getString("walk"));
        speed.setFly(rowSet.getString("fly"));
        speed.setHover(rowSet.getString("hover"));
        speed.setBurrow(rowSet.getString("burrow"));
        speed.setClimb(rowSet.getString("climb"));
        speed.setSwim(rowSet.getString("swim"));

        return speed;
    }

    private Senses mapRowToSenses(SqlRowSet rowSet){
        Senses senses = new Senses();

        senses.setDarkvision(rowSet.getString("darkvision"));
        senses.setBlindsight(rowSet.getString("blindsight"));
        senses.setTremorsense(rowSet.getString("tremorsense"));
        senses.setTruesight(rowSet.getString("truesight"));
        senses.setPassivePerception(rowSet.getInt("passive_perception"));

        return senses;
    }
}
