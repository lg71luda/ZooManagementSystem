package zoo.service;

import zoo.db.DatabaseConnection;
import java.sql.*;

public class AuthService {
    private static String currentUserRole = null;
    private static String currentUserName = null;

    public static boolean login(String login, String password) {
        String sql = "SELECT first_name, role FROM Staff WHERE login = ? AND password_hash = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, password); // временно храним пароль как есть

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                currentUserName = rs.getString("first_name");
                currentUserRole = rs.getString("role");
                System.out.println("Добро пожаловать, " + currentUserName + " (роль: " + currentUserRole + ")!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        }
        System.out.println("Неверный логин или пароль!");
        return false;
    }

    public static boolean hasRole(String role) {
        return role.equalsIgnoreCase(currentUserRole);
    }

    public static String getCurrentUserRole() { return currentUserRole; }

    public static void logout() {
        currentUserRole = null;
        currentUserName = null;
        System.out.println("До свидания!");
    }
}