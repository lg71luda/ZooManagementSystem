package zoo.db;

import java.sql.*;

public class EnclosureDAO {
    public void addEnclosure(String name, int capacity, String climate, double area, String location) {
        String sql = "INSERT INTO Enclosures (name, capacity, climate_type, area_sqm, location) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, capacity);
            ps.setString(3, climate);
            ps.setDouble(4, area);
            ps.setString(5, location);
            ps.executeUpdate();
            System.out.println("Вольер «" + name + "» добавлен");
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении вольера: " + e.getMessage());
        }
    }
}
