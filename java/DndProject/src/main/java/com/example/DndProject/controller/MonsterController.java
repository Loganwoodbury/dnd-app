package com.example.DndProject.controller;

import com.example.DndProject.Models.Monster.Monster;
import com.example.DndProject.Models.Monster.Results;
import com.example.DndProject.services.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @RequestMapping(path = "/monster/api", method = RequestMethod.GET)
    public List<Results> getMonsters(){
        return monsterService.getMonsters();
    }

    @RequestMapping(path = "/monster/api/{name}")
    public Monster getMonsterByName(@PathVariable String name){
        return monsterService.getMonsterByName(name);
    }

}
