package org.example.service;

import org.example.model.Animal;
import org.example.serializer.AnimalSerializer;
import org.example.util.Commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShelterService {

    private final File shelter;
    private final AnimalSerializer serializer;
    List<Animal> animals;

    public ShelterService(File shelter, AnimalSerializer serializer) {
        try {
            this.serializer = serializer;
            this.shelter = shelter;
            if (!shelter.exists()) {
                shelter.createNewFile();
                this.animals = new ArrayList<>();
            } else {
                this.animals = serializer.deserializeList(this.shelter);
            }
        } catch (
                IOException e) {
            throw new RuntimeException(Commands.DESERIALIZE_ERROR.toString(), e);
        }
    }

    public boolean addAnimal(Animal animal) {
        if (animal != null) {
            animals.add(animal);
            saveAnimals();
            return true;
        } else {
            return false;
        }
    }

    public boolean takeAnimal(String animalName) {
        if (animalName == null || animalName.isBlank()) {
            return false;
        }
        boolean removed = animals.removeIf(animal ->
                animal.getName() != null && animalName.equalsIgnoreCase(animal.getName()));
        if (removed) {
            saveAnimals();
        }
        return removed;
    }

    public List<Animal> showAll() {
        if (animals.isEmpty()) {
            System.out.println(Commands.EMPTY_FILE);
            return new ArrayList<>();
        }
        return animals;
    }

    public void saveAnimals() {
        try {
            serializer.serializeList(animals, shelter);
        } catch (IOException e) {
            throw new RuntimeException(Commands.ERROR_SAVING_DATA.toString(), e);
        }
    }
}
