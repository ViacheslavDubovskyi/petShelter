package org.example;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.serializer.AnimalSerializer;
import org.example.service.ShelterMenuService;
import org.example.service.ShelterService;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File shelter = new File("src/main/resources/shelter.json");
        AnimalSerializer jsonSerial = new AnimalSerializer(new JsonMapper());
        ShelterService shelterService = new ShelterService(shelter, jsonSerial);
        Scanner scanner = new Scanner(System.in);
        ShelterMenuService menuService = new ShelterMenuService(scanner, shelterService);

        menuService.run();

    }
}