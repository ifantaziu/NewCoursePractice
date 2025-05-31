package IndividualWork;

import java.sql.*;
import java.util.Scanner;

import static IndividualWork.Validator.*;

import java.sql.Connection;


public class Onboarding {
    private static final Scanner scanner = new Scanner(System.in);
    private final Connection connection;

    public Onboarding(Connection connection) {
        this.connection = connection;
    }

    public User onboardNewUser() {
        try {
            System.out.println("Welcome to your lifetime bank!");
            String fullName = onboardFullName();
            String idNo = onboardIdNo();
            String email = onboardEmail();
            String phone = onboardPhone();
            String username = onboardUsername();
            String password = onboardPassword();
            String clientId = IdGenerator.generateClientId();
            // Create user
            //User user = new User(fullName, idNo, email, phone, username, password, clientId);

            String sql = "INSERT INTO users (idno, username, password, full_name, email, phone, bankClientId) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Register user
            // UserRegistry.registerUser(user);

            // System.out.println("\n User created successfully:");
            // System.out.println(user);

            // return user;
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, idNo);
                stmt.setString(2, username);
                stmt.setString(3, password);
                stmt.setString(4, fullName);
                stmt.setString(5, email);
                stmt.setString(6, phone);
                stmt.setString(7, clientId);
                stmt.executeUpdate();
            }

            System.out.println("User registered successfully. Your Client ID is: " + clientId);
            return new User(idNo, username, password, fullName, email, phone, clientId);

        } catch (SQLException e) {
            System.err.println("Error during registration: " + e.getMessage());
            return null;
        }
    }

    private String onboardFullName() {
        System.out.print("For registration, please enter full name: ");
        return scanner.nextLine();
    }

    private String onboardIdNo() {
        while (true) {
            System.out.print("Enter personal ID number (IDNO) with 13 digits: ");
            String idNo = scanner.nextLine();
            if (!isValidIdNo(idNo)) {
                System.out.println("Error: The IDNO should contain exactly 13 digits.");
                continue;
            }
            if (isIdNoTaken(idNo)) {
                System.out.println("This IDNO is already registered. Please try another.");
                continue;
            }
            System.out.println("IDNO accepted: " + idNo);
            return idNo;
        }
    }

    private String onboardEmail() {
        while (true) {
            System.out.print("Enter email (e.g. examplename@mail.com): ");
            String email = scanner.nextLine();

            if (!isValidEmail(email)) {
                System.out.println(getInvalidEmailInputMessage(email));
                continue;
            }
            if (isEmailTaken(email)) {
                System.out.println("This email is already registered. Please use another.");
                continue;
            }
            System.out.println("Email accepted: " + email);
            return email;
        }
    }

    private String onboardPhone() {
        while (true) {
            System.out.print("Enter local phone number starting with 0: ");
            String phone = scanner.nextLine();
            if (!isValidPhoneNumber(phone)) {
                System.out.println("Error: Phone number must start with 0 and have 9 digits.");
                continue;
            }
            if (isPhoneTaken(phone)) {
                System.out.println("This phone number is already registered.");
                continue;
            }
            System.out.println("Phone number accepted: " + phone);
            return phone;
        }
    }

    private String onboardUsername() {
        while (true) {
            System.out.print("Please choose a username: ");
            String username = scanner.nextLine();
            if (!UserRegistry.usernameExists(username)) {
                return username;
            }
            System.out.println("Username already exists. Please choose another.");
        }
    }

    private String onboardPassword() {
        while (true) {
            System.out.print("Please choose a password: ");
            String password = scanner.nextLine();
            if (isValidPassword(password)) {
                System.out.println("Password is valid!");
                return password;
            } else {
                System.out.println("The password needs to have:" +
                        "\n- at least 8 characters;" +
                        "\n- one uppercase (A-Z);" +
                        "\n- one digit (0-9);" +
                        "\n- one special character (!@#$%^&*)." +
                        "\nPlease try again.");
            }
        }
    }

    public User loginUser() {
        try {
            System.out.print("For login, please enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String idno = rs.getString("idno");
                    String fullName = rs.getString("full_name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String clientId = rs.getString("clientId");

                    System.out.println("Login successful. Welcome, " + fullName + "!");
                    return new User(idno, username, password, fullName, email, phone, clientId);
                } else {
                    System.out.println("Invalid username or password.");
                    return null;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
            return null;
        }
    }
}




