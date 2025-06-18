package org.example.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Animal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimalSerializer {

    private final ObjectMapper mapper;

    public AnimalSerializer(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    public void serializeList(List<Animal> animals, File file) throws IOException {
        this.mapper.writeValue(file, animals);
    }

    public List<Animal> deserializeList(File file) throws IOException {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        return this.mapper.readValue(file, new TypeReference<List<Animal>>() {
        });
    }
}
