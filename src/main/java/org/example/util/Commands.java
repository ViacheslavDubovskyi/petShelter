package org.example.util;

public enum Commands {

    MENU("""
            Enter the command:
            leave - Add pet to shelter
            take - Take pet from the shelter
            show - Show all pets
            exit - Exit from the shelter
            """),
    LIST("List of animals: "),
    INVALID_COMMAND("Invalid command. Try again."),
    DESERIALIZE_ERROR("Error deserializing shelter."),
    EMPTY_FILE("Shelter is empty."),
    ERROR_SAVING_DATA("Error saving shelter data.");

    private final String message;

    Commands(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
