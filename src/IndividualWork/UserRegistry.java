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
}