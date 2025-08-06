package org.example.service;

import org.example.model.Animal;
import org.example.util.Commands;
import org.example.util.PetCard;

import java.util.Scanner;

public class ShelterMenuService {

    private final Scanner scanner;
    private final ShelterService shelterService;

    public ShelterMenuService(Scanner scanner, ShelterService shelterService) {
        this.scanner = scanner;
        this.shelterService = shelterService;
    }

    public void run() {
        scanner.useDelimiter("\n");
        while (true) {
            System.out.println("\n" + Commands.MENU);
            String userCommand = scanner.next();
            switch (userCommand) {
                case "leave" -> {
                    if (shelterService.addAnimal(AnimalCard()))
                        System.out.println(Commands.SUCCESS);
                }
                case "take" -> {
                    System.out.println(PetCard.NAME.getPrompt());
                    if (shelterService.takeAnimal(scanner.next())) {
                        System.out.println(Commands.SUCCESS);
                    }
                }
                case "show" -> {
                    System.out.println(Commands.LIST);
                    System.out.println(shelterService.showAll());
                }
                case "exit" -> {
                    shelterService.saveAnimals();
                    System.out.println(Commands.FAREWELL);
                    return;
                }
                default -> System.out.println(Commands.INVALID_COMMAND);
            }
        }
    }

    private Animal AnimalCard() {
        Animal.AnimalBuilder animalBuilder = Animal.builder();
        for (PetCard field : PetCard.values()) {
            System.out.println(field.getPrompt());
            while (true) {
                String userInput = scanner.nextLine().trim();
                if (field != PetCard.AGE) {
                    if (!isValidString(userInput, field)) {
                        continue;
                    }
                    switch (field) {
                        case NAME -> animalBuilder.name(userInput);
                        case GENDER -> animalBuilder.gender(userInput);
                        case TYPE -> animalBuilder.type(userInput);
                        case BREED -> animalBuilder.breed(userInput);
                    }
                } else {
                    if (!isValidAge(userInput)) {
                        continue;
                    }
                    animalBuilder.age(Integer.parseInt(userInput));
                }
                break;
            }
        }
        return animalBuilder.build();
    }

    private boolean isValidString(String input, PetCard field) {
        if (input == null || input.isBlank()) {
            System.out.println("Error: " + field.name() + " cannot be empty or consist of only whitespace.");
            return false;
        }
        return true;
    }

    private boolean isValidAge(String input) {
        try {
            int age = Integer.parseInt(input);
            return age > 0;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid positive number for age.");
            return false;
        }
    }
}

