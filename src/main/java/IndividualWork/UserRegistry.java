package IndividualWork;

import java.util.HashMap;
import java.util.Map;

public class UserRegistry {
    private static final Map<String, User> registeredUsers = new HashMap<>();

    public static boolean usernameExists(String username) {
        return registeredUsers.containsKey(username);
    }

    public static void registerUser(User user) {
        registeredUsers.put(user.getUsername(), user);
    }

    public static User getUserByUsername(String username) {
        return registeredUsers.get(username);
    }

    public static void listAllUsers() {
        registeredUsers.values().forEach(System.out::println);
    }
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&*])[A-Za-z0-9!@#\\$%\\^&*]{8,}$";
        return password.matches(regex);
    }

    public static boolean isValidIdNo(String idNo) {
        return idNo.matches("^[0-9]{13}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^0[0-9]{8}$");
    }

    public static boolean isValidEmail(String email) {
        String validEmailCharacters = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(validEmailCharacters);
    }

    public static String getInvalidEmailInputMessage(String email) {
        if (!email.contains("@")) {
            return "Error: Email must contain the '@' symbol.";
        }
        if (!email.contains(".")) {
            return "Error: Email domain must contain a '.' (e.g., '.com', '.org').";
        }
        if (email.indexOf('@') != email.lastIndexOf('@')) {
            return "Error: Email cannot contain multiple '@' symbols.";
        }
        if (email.startsWith("@") || email.endsWith("@")) {
            return "Error: Email cannot start or end with '@'.";
        }
        return "Error: Invalid email format.";
    }

}