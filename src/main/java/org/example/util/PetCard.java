package org.example.util;

public enum PetCard {

    NAME("name"),
    AGE("age"),
    BREED("breed"),
    GENDER("gender"),
    TYPE("type");

    private final String field;

    PetCard(String field) {
        this.field = field;
    }

    public String getPrompt() {
        return "Enter the " + field + " of the animal: ";
    }
}
