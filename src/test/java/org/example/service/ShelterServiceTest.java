package org.example.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.model.Animal;
import org.example.serializer.AnimalSerializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShelterServiceTest {

    public final AnimalSerializer serializer = new AnimalSerializer(new JsonMapper());
    public final File shelter = new File("src/test/resources/shelter_test.json");
    public final ShelterService shelterService = new ShelterService(shelter, serializer);

    @Before
    public void init_File() throws IOException {
        this.shelter.createNewFile();
        shelterService.animals = get_animals_for_test();
    }

    @After
    public void delete_File() {
        this.shelter.delete();
    }

    public List<Animal> get_animals_for_test() {
        List<Animal> animals = new ArrayList<>();
        animals.add(Animal.builder()
                .name("pan")
                .age(3)
                .type("dog")
                .breed("stray")
                .gender("male")
                .build());
        animals.add(Animal.builder()
                .name("lisa")
                .age(5)
                .type("cat")
                .breed("stray")
                .gender("female")
                .build());

        return animals;
    }

    @Test
    public void add_animal() {
        Animal animal = Animal.builder()
                .name("pan")
                .age(3)
                .type("dog")
                .breed("stray")
                .gender("male")
                .build();

        boolean result = shelterService.addAnimal(animal);

        assertTrue(result);
        assertTrue(shelter.length() != 0);
    }

    @Test
    public void add_animal_null() {
        boolean result = shelterService.addAnimal(null);

        assertFalse(result);
        assertTrue(shelter.length() == 0);
    }

    @Test
    public void take_animal() {
        String removeName = "pan";

        boolean result = shelterService.takeAnimal(removeName);

        assertTrue(result);
        assertEquals(1, shelterService.animals.size());
    }

    @Test
    public void take_animal_null() {
        boolean result = shelterService.takeAnimal(null);

        assertFalse(result);
        assertEquals(2, shelterService.animals.size());
    }

    @Test
    public void show_animals() {
        List<Animal> animals = get_animals_for_test();
        List<Animal> result = shelterService.showAll();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals(animals.get(0), result.get(0));

    }

    @Test
    public void show_animals_null() {
        shelterService.animals = new ArrayList<>();

        List<Animal> result = shelterService.showAll();
        assertTrue(result.isEmpty());
    }

    @Test
    public void save_animals() throws IOException {
        List<Animal> animals = shelterService.animals;
        shelterService.saveAnimals();

        List<Animal> deserialized = serializer.deserializeList(shelter);
        Assert.assertEquals(animals, deserialized);
    }
}

