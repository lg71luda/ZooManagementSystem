package zoo.model;

import java.time.LocalDate;

public class Animal {
    private Integer animalId;
    private String species;
    private String name;
    private LocalDate birthDate;
    private String gender;         // "Male" или "Female"
    private String healthStatus;
    private Integer enclosureId;
    private Integer veterinarianId;

    // Конструктор по умолчанию (обязателен для JDBC)
    public Animal() {}

    // Конструктор со всеми полями (удобно для тестов)
    public Animal(String species, String name, LocalDate birthDate, String gender) {
        this.species = species;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.healthStatus = "Healthy";
    }

    // Геттеры и сеттеры (можно сгенерировать автоматически: Alt+Insert → Getter and Setter)
    public Integer getAnimalId() { return animalId; }
    public void setAnimalId(Integer animalId) { this.animalId = animalId; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getHealthStatus() { return healthStatus; }
    public void setHealthStatus(String healthStatus) { this.healthStatus = healthStatus; }

    public Integer getEnclosureId() { return enclosureId; }
    public void setEnclosureId(Integer enclosureId) { this.enclosureId = enclosureId; }

    public Integer getVeterinarianId() { return veterinarianId; }
    public void setVeterinarianId(Integer veterinarianId) { this.veterinarianId = veterinarianId; }

    @Override
    public String toString() {
        return String.format("Animal{id=%d, вид='%s', кличка='%s', пол=%s, статус=%s}",
                animalId, species, name, gender, healthStatus);
    }
}