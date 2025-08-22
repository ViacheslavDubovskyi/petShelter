🐾 PetShelter

A console-based Java application for managing pet intake and adoption in small shelters.
The project follows a layered architecture and uses JSON file serialization for persistent storage.

📌 Features

* Add new pets to the system (Create)
* View pet list (Read)
* Update pet information (Update)
* Remove pet records (Delete)
* Save and load data via JSON serialization

🏗 Architecture

The app is organized into several packages:

* model – entity classes (e.g., Animal)
* serializer – JSON serialization (AnimalSerializer)
* service – business logic & menu handling (ShelterService, ShelterMenuService)
* util – supporting ENAM classes (Commands, PetCard)
* Main – entry point of the application

 🛠️ Technologies

* Language: Java
* Build Tool: Maven
* Serialization: JSON
* Testing: JUnit

💾 Data Storage

* Pet data is serialized to JSON (e.g., via Jackson/Gson).
* Data is loaded from a file on startup and persisted after changes.

🧪 Testing

Unit tests with JUnit, located under src/test/java/org/example.

⚙️ Build & Run

The project is built with Maven.

Build
```
mvn clean install
```
Run
```
mvn exec:java -Dexec.mainClass="org.example.Main"
```
