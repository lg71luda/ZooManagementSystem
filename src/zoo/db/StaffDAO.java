package zoo.db;

import java.sql.*;

public class StaffDAO {
    public boolean create(String login, String passwordHash, String firstName, String lastName, String role) {
        String sql = "INSERT INTO Staff (login, password_hash, first_name, last_name, role) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, passwordHash);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, role.toUpperCase());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении сотрудника: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
