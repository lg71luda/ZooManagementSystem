package zoo.db;

import java.sql.*;

public class TicketDAO {
    public void sellTicket(String type, double price, String visitorInfo) {
        String sql = "INSERT INTO Tickets (ticket_type, price, visitor_info) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);
            ps.setDouble(2, price);
            ps.setString(3, visitorInfo);
            ps.executeUpdate();
            System.out.println("Билет «" + type + "» продан за " + price + " ₽");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
