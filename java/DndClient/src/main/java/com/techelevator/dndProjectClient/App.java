package com.techelevator.dndProjectClient;

import com.techelevator.dndProjectClient.services.ConsoleService;
import com.techelevator.dndProjectClient.services.MonsterService;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final MonsterService monsterService = new MonsterService();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        mainMenu();
    }


    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewAllMonsters();
            } else if (menuSelection == 2) {
                viewMonsterByName();
            }else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewAllMonsters() {
		monsterService.getAllMonsters();
		
	}

	private void viewMonsterByName() {
		String name = consoleService.promptForString("Enter a monster name to find: ");
        monsterService.getMonsterByName(name);
		
	}


}
