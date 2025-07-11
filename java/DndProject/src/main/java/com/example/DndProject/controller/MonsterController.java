package com.example.DndProject.controller;

import com.example.DndProject.Models.Monster.Monster;
import com.example.DndProject.Models.Monster.Results;
import com.example.DndProject.services.MonsterService;
import com.example.DndProject.services.RestMonsterService;
import org.springframework.aot.generate.AccessControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MonsterController {

    @Autowired
    private RestMonsterService restMonsterService;

    @Autowired
    private MonsterService monsterService;


    @RequestMapping(path = "/monster/api", method = RequestMethod.GET)
    public List<Results> getMonsters(){
        return restMonsterService.getMonsters();
    }

    @RequestMapping(path = "/monster/api/{name}")
    public Monster getMonsterByName(@PathVariable String name){
        return restMonsterService.getMonsterByName(name);
    }

    @PostMapping("/monster/add")
    public Monster createMonster(@RequestBody Monster newMonster){
        Monster monster = null;

        monster = monsterService.createMonster(newMonster);

        return monster;
    }

    @GetMapping("/monster/{name}")
    public Monster getServerMonsterByName(@PathVariable String name){
        Monster monster = null;

        monster = monsterService.getMonsterByName(name);

        return monster;
    }

}
