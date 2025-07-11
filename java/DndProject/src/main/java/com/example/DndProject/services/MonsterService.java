package com.example.DndProject.services;

import com.example.DndProject.Models.Monster.Monster;
import com.example.DndProject.dao.MonsterDao;
import org.springframework.stereotype.Component;

@Component
public class MonsterService {

    private final MonsterDao monsterDao;

    public MonsterService(MonsterDao monsterDao) {
        this.monsterDao = monsterDao;
    }

    public Monster createMonster(Monster monster){ return monsterDao.createMonster(monster);}
    public Monster getMonsterById(int monsterId){ return monsterDao.getMonsterById(monsterId);}

    public Monster getMonsterByName(String name) {
        return monsterDao.getMonsterByName(name);
    }
}
