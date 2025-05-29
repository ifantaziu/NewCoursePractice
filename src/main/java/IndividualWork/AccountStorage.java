package IndividualWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AccountStorage {
    private static final Logger logger = LoggerFactory.getLogger(AccountStorage.class);
    private static final Map<User, List<Accounts>> userAccounts = new HashMap<>();
    private static final Map<User, Map<Currency, CurrencyCashOutAccount>> userCurrencyCashOutAccounts = new HashMap<>();

    public static void addAccount(User user, Accounts account) {
        if (user == null || account == null) {
            throw new IllegalArgumentException("User and account must not be null.");
        }
        userAccounts.computeIfAbsent(user, k -> new ArrayList<>()).add(account);
    }

    public static List<Accounts> getUserAccounts(User user) {
        return new ArrayList<>(userAccounts.getOrDefault(user, new ArrayList<>()));
    }

    public static Accounts findAccountByIban(User user, String iban) {
        if (user == null || iban == null || iban.isEmpty()) {
            throw new IllegalArgumentException("User and IBAN must not be null or empty.");
        }

        return getUserAccounts(user).stream()
                .filter(acc -> iban.equalsIgnoreCase(acc.getIban()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Account not found for IBAN: " + iban));
    }


    public static void showAllAccounts(User user) {
        List<Accounts> accounts = getUserAccounts(user);
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("List of all accounts for user: " + user.getFullName());
        for (Accounts acc : accounts) {
            //System.out.printf("Account: %s | Holder: %s%n", acc.getIban(), user.getFullName());
            logger.info("Account: %s | Holder: %s%n" + acc.getIban() + user.getFullName());
            acc.checkAccountBalance();
        }
    }

    public static void removeAccount(User user, String iban) {
        Accounts toRemove = findAccountByIban(user, iban);
        userAccounts.get(user).remove(toRemove);
        System.out.println("Account " + iban + " removed successfully.");
    }

    public static void initializeCurrencyAccount(User user) {
        Map<Currency, CurrencyCashOutAccount> cashOutAccounts = new HashMap<>();
        for (Currency currency : Currency.values()) {
            cashOutAccounts.put(currency, new CurrencyCashOutAccount(currency));
        }
        userCurrencyCashOutAccounts.put(user, cashOutAccounts);
    }

    public static CurrencyCashOutAccount getCurrencyCashOutAccount(User user, Currency currency) {
        return userCurrencyCashOutAccounts.getOrDefault(user, new HashMap<>()).get(currency);
    }
}