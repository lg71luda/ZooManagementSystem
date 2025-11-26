package zoo.db;

import zoo.model.Animal;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    // 1. Добавление животного
    public void addAnimal(Animal animal) {
        String sql = "INSERT INTO Animals (species, name, birth_date, gender, health_status, enclosure_id, veterinarian_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, animal.getSpecies());
            ps.setString(2, animal.getName());
            ps.setObject(3, animal.getBirthDate());
            ps.setString(4, animal.getGender());
            ps.setString(5, animal.getHealthStatus());
            ps.setObject(6, animal.getEnclosureId());
            ps.setObject(7, animal.getVeterinarianId());

            ps.executeUpdate();

            // Получаем сгенерированный ID и устанавливаем в объект
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                animal.setAnimalId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении животного: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 2. Получение всех животных
    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM Animals ORDER BY species, name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Animal a = new Animal();
                a.setAnimalId(rs.getInt("animal_id"));
                a.setSpecies(rs.getString("species"));
                a.setName(rs.getString("name"));
                a.setBirthDate(rs.getObject("birth_date", LocalDate.class));
                a.setGender(rs.getString("gender"));
                a.setHealthStatus(rs.getString("health_status"));
                a.setEnclosureId(rs.getObject("enclosure_id", Integer.class));
                a.setVeterinarianId(rs.getObject("veterinarian_id", Integer.class));
                animals.add(a);
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при получении списка животных: " + e.getMessage());
        }
        return animals;
    }
}