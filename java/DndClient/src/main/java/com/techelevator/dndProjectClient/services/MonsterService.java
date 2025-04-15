package com.techelevator.dndProjectClient.services;

import com.techelevator.dndProjectClient.model.Monster.Monster;
import com.techelevator.util.BasicLogger;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

public class MonsterService {

    private static final String API_BASE_URL = "http://localhost:8080/monster";
    private final RestClient restClient = RestClient.create(API_BASE_URL);

    public void getAllMonsters(){

        Monster[] monsters = null;

        try {
            monsters = restClient.get()
                    .uri("/api" )
                    .retrieve()
                    .body(Monster[].class);

        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }

        for(Monster monster : monsters){
            System.out.println(monster.getName());
        }

    }

    public void getMonsterByName(String name){

            Monster monster = null;

            try {
                monster = restClient.get()
                        .uri("/api/" + name )
                        .retrieve()
                        .body(Monster.class);

            } catch (RestClientResponseException | ResourceAccessException e) {
                BasicLogger.log(e.getMessage());
            }

        System.out.println(monster.toString());

    }
}
