package IndividualWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static IndividualWork.User.getFullname;


public class AccountStorage {
    private static final Logger logger = LoggerFactory.getLogger(AccountStorage.class);
    private static final Map<User, List<Accounts>> userAccounts = new HashMap<>();
    private static final Map<User, Map<Currency, CurrencyCashOutAccount>> userCurrencyCashOutAccounts = new HashMap<>();
    private final Connection connection;

    public AccountStorage(Connection connection) {
        this.connection = connection;
    }
//    public static void main(String[] args) {
//        User user = new User("John Doe", "7776665554345", "CL-115844", "joe@mai.com", "010776333", "hgjhk", "jhgf@fghAGD5678");
//        Accounts acc = new GeneralAccount("7776665554345", "John Doe", 100.0);
//        AccountStorage.addAccount(user, acc);
//    }

    // public static void addAccount(String username, String fullName, double balance, String type, boolean issueCard, String deliveryAddress) {
    public Accounts addAccount(User user, Accounts account) {
        String iban;

        if (account instanceof GeneralAccount) {
            iban = IdGenerator.generateIban("general");
        } else if (account instanceof SavingsAccount) {
            iban = IdGenerator.generateIban("savings");
        } else if (account instanceof CardAccount) {
            iban = IdGenerator.generateIban("card");
        } else {
            System.out.println("Unknown account type.");
            return account;
        }


        String sql = "INSERT INTO accounts (iban, accounttype, username, balance, fullname, issuecard, deliveryaddress) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, iban);
            ps.setString(2, account.getAccounttype());
            ps.setString(3, user.getUsername());
            ps.setDouble(4, account.getBalance());
            ps.setString(5, user.getFullname());
            ps.setBoolean(6, account instanceof CardAccount);
            ps.setString(7, (account instanceof CardAccount) ? ((CardAccount) account).getCardDeliveryAddress() : null);

            System.out.println(">> Se execută INSERT în baza de date...");
            ps.executeUpdate();
            System.out.println("Account created successfully with IBAN: " + iban);
        } catch (SQLException e) {
            System.out.println("Account creation failed: " + e.getMessage());
        }
        return account;
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


        System.out.println("List of all accounts for user: " + getFullname());
        for (Accounts acc : accounts) {
            //System.out.printf("Account: %s | Holder: %s%n", acc.getIban(), user.getFullName());
            logger.info("Account: %s | Holder: %s%n" + acc.getIban() + getFullname());

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