package com.example.DndProject.dao;

import com.example.DndProject.Models.Monster.Monster;

import java.util.List;

public interface MonsterDao {

    List<Monster> getAllMonsters();

    Monster getMonsterByName(String name);

    Monster createMonster(Monster monster);

    Monster getMonsterById(int monsterId);

}
