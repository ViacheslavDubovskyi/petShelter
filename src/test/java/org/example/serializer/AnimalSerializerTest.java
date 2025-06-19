package org.example.serializer;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.model.Animal;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AnimalSerializerTest {

    public final AnimalSerializer serializer = new AnimalSerializer(new JsonMapper());
    File shelter = new File("src/test/resources/shelter_test.json");

    @Before
    public void init_File() throws IOException {
        this.shelter.createNewFile();
    }

    @After
    public void delete_File() {
        this.shelter.delete();
    }

    public List<Animal> get_animals_for_test() {
        return Stream.of(
                Animal.builder()
                        .name("pan")
                        .age(3)
                        .type("dog")
                        .breed("stray")
                        .gender("male")
                        .build(),
                Animal.builder()
                        .name("lisa")
                        .age(5)
                        .type("cat")
                        .breed("stray")
                        .gender("female")
                        .build()).toList();
    }

    @Test
    public void serialize_list() throws Exception {
        List<Animal> animals = get_animals_for_test();
        serializer.serializeList(animals, shelter);
        List<Animal> deserialized = serializer.deserializeList(shelter);

        Assert.assertTrue(shelter.exists());
        Assert.assertEquals(animals, deserialized);
    }

    @Test
    public void deserialize_list() throws Exception {
        List<Animal> animals = get_animals_for_test();
        serializer.serializeList(animals, shelter);
        List<Animal> deserialized = serializer.deserializeList(shelter);

        Assert.assertTrue(shelter.exists());
        Assert.assertEquals(animals, deserialized);
    }

    @Test
    public void deserialize_empty_list() throws Exception {
        List<Animal> animals = new ArrayList<>();
        serializer.serializeList(animals, shelter);
        List<Animal> deserialized = serializer.deserializeList(shelter);

        Assert.assertTrue(shelter.exists());
        Assert.assertEquals(animals, deserialized);
    }
}
