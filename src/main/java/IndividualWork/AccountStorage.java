package IndividualWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountStorage {
    private static final Map<User, List<Accounts>> userAccounts = new HashMap<>();

    public static void addAccount(User user, Accounts account) {
        userAccounts.computeIfAbsent(user, k -> new ArrayList<>()).add(account);
    }

    public static List<Accounts> getUserAccounts(User user) {
        return userAccounts.getOrDefault(user, new ArrayList<>());
    }
}
