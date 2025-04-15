package com.techelevator.dndProjectClient.services;


import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*****************************");
        System.out.println("* Welcome to DnD Compendium *");
        System.out.println("*****************************");
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View all monsters");
        System.out.println("2: View monster by name");
        System.out.println("0: Exit");
        System.out.println();
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        String userInput = scanner.nextLine();

        if(userInput.contains(" ")){
            userInput = userInput.replaceAll(" ", "-");
        }
        return userInput;
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

}
