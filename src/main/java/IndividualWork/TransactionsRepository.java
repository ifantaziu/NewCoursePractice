package IndividualWork;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TransactionsRepository {
    private static final Logger logger = Logger.getLogger(TransactionsRepository.class.getName());
    private static Connection connection = DBConnect.getConnection();

    public TransactionsRepository(Connection connection) {
        this.connection = connection;
    }

    public static void recordTransaction(String username, String sourceiban, String destinationiban, String type,
                                         double amount, String currency, Timestamp timestamp, String details) {
        String sql = "INSERT INTO transactions (username, sourceiban, destinationiban, type, amount, currency, timestamp, details) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, sourceiban);
            ps.setString(3, destinationiban);
            ps.setString(4, type);
            ps.setDouble(5, amount);
            ps.setString(6, currency);
            ps.setTimestamp(7, timestamp);
            ps.setString(8, details);
            ps.executeUpdate();
            logger.info(String.format("TRANSACTION - Type: %s | From: %s | To: %s | Amount: %.2f %s | Username: %s | Desc: %s",
                    type,
                    sourceiban != null ? sourceiban : "-",
                    destinationiban != null ? destinationiban : "-",
                    amount,
                    currency,
                    username,
                    details
            ));
        } catch (SQLException e) {
            logger.severe("Failed to record transaction: " + e.getMessage());
        }
    }

    public static List<String> getTransactionsByIban(String iban) {
        List<String> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE sourceiban = ? OR destinationiban = ? ORDER BY timestamp DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, iban);
            ps.setString(2, iban);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transactions.add(formatTransaction(rs));
            }
        } catch (SQLException e) {
            logger.severe("Error fetching transactions by IBAN: " + e.getMessage());
        }
        return transactions;
    }

    public static List<String> getTransactionsByUser(String username) {
        List<String> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE username = ? ORDER BY timestamp DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transactions.add(formatTransaction(rs));
            }
        } catch (SQLException e) {
            logger.severe("Error fetching transactions by user: " + e.getMessage());
        }
        return transactions;
    }

    private static String formatTransaction(ResultSet rs) throws SQLException {
        return String.format("[%s] %s | From: %s | To: %s | Amount: %.2f %s | Desc: %s",
                rs.getTimestamp("timestamp"),
                rs.getString("transactiontype"),
                rs.getString("sourceiban") != null ? rs.getString("sourceiban") : "-",
                rs.getString("destinationiban") != null ? rs.getString("destinationiban") : "-",
                rs.getDouble("amount"),
                rs.getString("currency"),
                rs.getString("details"));
    }
}
