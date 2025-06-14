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
        System.out.println(Commands.MENU);
        String userCommand = scanner.next();
        while (true) {
            switch (userCommand) {
                case "leave":
                    shelterService.addAnimal(AnimalCard());
                    break;
                case "take":
                    System.out.println(PetCard.NAME.getPrompt());
                    shelterService.takeAnimal(scanner.next());
                    break;
                case "show":
                    System.out.println(Commands.LIST);
                    shelterService.showAll();
                    break;
                case "exit":
                    shelterService.saveAnimals();
                    return;
                default:
                    System.out.println(Commands.INVALID_COMMAND);
            }
        }
    }

    private Animal AnimalCard() {
        Animal.AnimalBuilder animalBuilder = Animal.builder();
        for (PetCard field : PetCard.values()) {
            System.out.println(field.getPrompt());
            if (scanner.hasNext()) {
                switch (field) {
                    case NAME -> animalBuilder.name(scanner.next());
                    case AGE -> {
                        if (scanner.hasNextInt())
                            animalBuilder.age(scanner.nextInt());
                    }
                    case GENDER -> animalBuilder.gender(scanner.next());
                    case TYPE -> animalBuilder.type(scanner.next());
                    case BREED -> animalBuilder.breed(scanner.next());
                }
            }
        }
        return animalBuilder.build();
    }
}

