package com.example.DndProject.services;

import com.example.DndProject.Models.Monster.Monster;
import com.example.DndProject.Models.Monster.Results;
import com.example.DndProject.Models.Monster.Root;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestMonsterService{

    private final String BASE_MONSTER_URL = "https://www.dnd5eapi.co/api/2014/monsters";
    private final RestClient monsterClient = RestClient.create(BASE_MONSTER_URL);
    private final String NAME_SEARCH = "?name=";
    private final String TYPE_SEARCH = "?type=";


    public List<Results> getMonsters(){
        List<Results> monsterList = null;
        monsterList = monsterClient.get()
                .retrieve()
                .body(Root.class).results;
        return monsterList;
    }

    public Monster getMonsterByName(String name){
        Monster monster = null;
        monster = monsterClient.get()
                .uri("/" + name)
                .retrieve()
                .body(Monster.class);

        return monster;
    }
}
