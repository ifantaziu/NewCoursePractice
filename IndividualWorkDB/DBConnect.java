package IndividualWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    //    public String url="jdbc:postgresql://localhost:5432/banking_app";
//    Properties properties = new Properties();
//properties.setProperty("user", "irina_f");
//properties.setProperty("password", "Primavara2025=");
    public static String urlWithCredentials = "jdbc:postgresql://localhost:5432/banking_app?user=irina_f&password=Primavara2025=";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(urlWithCredentials);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
}
