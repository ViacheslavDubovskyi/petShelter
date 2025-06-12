package org.example.service;

import java.util.Scanner;

public class ShelterMenuService {

    private final Scanner scanner;
    private final ShelterService shelterService;

    public ShelterMenuService(Scanner scanner, ShelterService shelterService) {
        this.scanner = scanner;
        this.shelterService = shelterService;
    }

}

