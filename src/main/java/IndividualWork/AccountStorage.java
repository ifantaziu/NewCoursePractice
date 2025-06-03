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


   // public static void addAccount(String username, String fullName, double balance, String type, boolean issueCard, String deliveryAddress) {
   public static void addAccount(User user, Accounts account) {
       String iban;

       if (account instanceof GeneralAccount) {
           iban = IdGenerator.generateIban("general");
       } else if (account instanceof SavingsAccount) {
           iban = IdGenerator.generateIban("savings");
       } else if (account instanceof CardAccount) {
           iban = IdGenerator.generateIban("card");
       } else {
           System.out.println("Unknown account type.");
           return;
       }

       try (Connection conn = new DBConnect().getConnection()) {
           String sql = "INSERT INTO accounts (username, fullname, iban, balance, type, issue_card, delivery_address) VALUES (?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement ps = conn.prepareStatement(sql);

           ps.setString(1, user.getUsername());
           ps.setString(2, user.getFullname());
           ps.setString(3, iban);
           ps.setDouble(4, account.getBalance());
           ps.setString(5, account.getAccountType());
           ps.setBoolean(6, account instanceof CardAccount);
           ps.setString(7, (account instanceof CardAccount) ? ((CardAccount) account).getCardDeliveryAddress() : null);

           ps.executeUpdate();
           System.out.println("Account created successfully with IBAN: " + iban);
       } catch (SQLException e) {
           System.out.println("Account creation failed: " + e.getMessage());
       }
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