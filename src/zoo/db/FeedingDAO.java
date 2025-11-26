package zoo.db;

import java.sql.*;

public class FeedingDAO {
    public void addFeeding(int animalId, int staffId, String foodType, double quantity, String notes) {
        String sql = "INSERT INTO Feedings (animal_id, staff_id, food_type, quantity, feeding_time, notes) " +
                "VALUES (?, ?, ?, ?, NOW(), ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, animalId);
            ps.setObject(2, staffId > 0 ? staffId : null);
            ps.setString(3, foodType);
            ps.setDouble(4, quantity);
            ps.setString(5, notes);
            ps.executeUpdate();
            System.out.println("Кормление записано");
        } catch (SQLException e) {
            System.err.println("Ошибка кормления: " + e.getMessage());
        }
    }
}