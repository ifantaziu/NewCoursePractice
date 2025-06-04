package IndividualWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public static boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, fullname, idno, email, phone, password, bank_client_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, User.getFullname());
            stmt.setString(3, user.getIdno());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getClientid());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public static User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("fullname"),
                        rs.getString("idno"),
                        rs.getString("bank_client_id"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            } else {
                return null; // login failed
            }

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return null;
        }
    }

    public static boolean usernameExists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error checking username: " + e.getMessage());
            return false;
        }
    }
    public static User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("fullname"),
                            rs.getString("idno"),
                            rs.getString("clientid"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("username"),
                            rs.getString("password")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user: " + e.getMessage(), e);
        }
    }
    public static void listAllUsers() {
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("fullname"),
                        rs.getString("idno"),
                        rs.getString("clientid"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listing users: " + e.getMessage(), e);
        }
    }
}
