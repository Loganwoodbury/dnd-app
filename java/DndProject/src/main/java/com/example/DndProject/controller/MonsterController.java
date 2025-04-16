package com.example.DndProject.controller;

import com.example.DndProject.Models.Monster.Monster;
import com.example.DndProject.Models.Monster.Results;
import com.example.DndProject.services.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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
