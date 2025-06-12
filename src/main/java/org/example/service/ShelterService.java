package org.example.service;

import org.example.model.Animal;
import org.example.serializer.AnimalSerializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShelterService {

    private final File shelter;
    private final AnimalSerializer serializer;
    private List<Animal> animals;

    public ShelterService(File shelter, AnimalSerializer serializer) {
        this.shelter = shelter;
        this.serializer = serializer;
        try {
            List<Animal> deserialized = serializer.deserializeList(this.shelter);
            this.animals = (deserialized != null) ? deserialized : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing shelter", e);
        }
    }

    public void addAnimal(Animal animal) {
        if (animal != null) {
            animals.add(animal);
            saveAnimals();
        }
    }

    public void takeAnimal(String animalName) {
        if (animalName == null) return;
        for (Animal animal : animals) {
            if (animalName.equalsIgnoreCase(animal.getName())) {
                animals.remove(animal);
                saveAnimals();
                break;
            }
        }
    }

    public List<Animal> showAll() {
        if (animals.isEmpty()) {
            System.out.println("Shelter is empty.");
            return null;
        }
        return animals;
    }

    public void saveAnimals() {
        try {
            serializer.serializeList(animals, shelter);
        } catch (IOException e) {
            throw new RuntimeException("Error saving shelter data", e);
        }
    }
}
