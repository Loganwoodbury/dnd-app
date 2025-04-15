package com.example.DndProject.services;

import com.example.DndProject.Models.Monster.Monster;
import com.example.DndProject.Models.Monster.Results;

import java.util.List;

public interface MonsterService {

    List<Results> getMonsters();

    Monster getMonsterByName(String name);
}
