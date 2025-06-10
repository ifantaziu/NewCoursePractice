package IndividualWork;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class AccountsRepository {

    private static Connection connection = DBConnect.getConnection();
    public AccountsRepository(Connection connection) {
        this.connection = connection;
    }

    public Accounts addAccount(User user, Accounts account) {
        String iban;

        if (account instanceof GeneralAccount) {
            iban = IdGenerator.generateIban("general");
        } else if (account instanceof SavingsAccount) {
            iban = IdGenerator.generateIban("savings");
        } else if (account instanceof CardAccount) {
            iban = IdGenerator.generateIban("card");
        } else if (account instanceof CurrencyCashOutAccount) {
            iban = IdGenerator.generateIban("currency");
        } else {
            System.out.println("Unknown account type.");
            return account;
        }

        String sql = """
                INSERT INTO accounts 
                (iban, accounttype, username, balance, fullname, issuecard, carddeliveryaddress, accountopeningdate, lastinterestdate) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, iban);
            ps.setString(2, account.getAccounttype());
            ps.setString(3, user.getUsername());
            ps.setDouble(4, account.getBalance());
            ps.setString(5, user.getFullname());

            // Card Account
            if (account instanceof CardAccount card) {
                ps.setBoolean(6, true);
                ps.setString(7, card.getCarddeliveryaddress());
            } else {
                ps.setBoolean(6, false);
                ps.setString(7, null);
            }

            // Savings account
            if (account instanceof SavingsAccount savings) {
                ps.setDate(8, Date.valueOf(LocalDate.now()));
                ps.setDate(9, Date.valueOf(LocalDate.now()));
            } else {
                ps.setDate(8, null);
                ps.setDate(9, null);
            }

            ps.executeUpdate();
            System.out.println("Account created successfully with IBAN: " + iban);

        } catch (SQLException e) {
            System.out.println("Account creation failed: " + e.getMessage());
        }

        return account;
    }

    public static Collection<Accounts> getAllAccountsForUser(String username) {
        Collection<Accounts> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE username = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("accounttype").toLowerCase();
                String iban = rs.getString("iban");
                String fullname = rs.getString("fullname");
                double balance = rs.getDouble("balance");

                switch (type) {
                    case "card" -> {
                        String carddeliveryAddress = rs.getString("carddeliveryaddress");
                        CardAccount card = new CardAccount(iban, fullname, balance, carddeliveryAddress);
                        accounts.add(card);
                    }
                    case "general" -> {
                        GeneralAccount general = new GeneralAccount(iban, fullname, balance);
                        accounts.add(general);
                    }
                    case "savings" -> {
                        LocalDate openingDate = rs.getDate("accountopeningdate").toLocalDate();
                        LocalDate lastInterestDate = rs.getDate("lastinterestdate").toLocalDate();
                        SavingsAccount savings = new SavingsAccount(iban, fullname, balance);

                        savings.setAccountopeningdate(openingDate);
                        savings.setLastinterestdate(lastInterestDate);
                        accounts.add(savings);
                    }
                    default -> System.out.println("Unknown account type: " + type);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving accounts: " + e.getMessage());
        }

        return accounts;
    }
    public static void updateAccountBalance(String iban, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE iban = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, iban);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Account balance updated successfully.");
            } else {
                System.out.println("No account found with the provided IBAN.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating account balance: " + e.getMessage());
        }
    }
    public static void deleteAccount(String iban) {
        String sql = "DELETE FROM accounts WHERE iban = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, iban);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("No account found with the provided IBAN.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting account: " + e.getMessage());
        }
    }
    public static Accounts findAccountByIban(String iban) {
        String sql = "SELECT * FROM accounts WHERE iban = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, iban);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String type = rs.getString("accounttype");
                String fullname = rs.getString("fullname");
                double balance = rs.getDouble("balance");

                switch (type) {
                    case "general":
                        return new GeneralAccount(iban, fullname, balance);
                    case "savings":
                        return new SavingsAccount(iban, fullname, balance);
                    case "card":
                        String carddeliveryAddress = rs.getString("carddeliveryaddress");
                        return new CardAccount(iban, fullname, balance, carddeliveryAddress);
                    case "currency":
                        String currencyStr = rs.getString("currency");
                        Currency currency = Currency.valueOf(currencyStr);
                        return new CurrencyCashOutAccount(currency, balance);
                    default:
                        System.out.println("Unknown account type found in database.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving account: " + e.getMessage());
        }
        return null;
    }
    public static void initializeCurrencyAccounts(User user) {
        for (Currency currency : Currency.values()) {
            if (doesCurrencyAccountExist(user.getUsername(), currency.name())) {
                continue;
            }

            CurrencyCashOutAccount account = new CurrencyCashOutAccount(currency, 0.0);
            String iban = account.getIban();

            String sql = "INSERT INTO accounts (iban, accounttype, username, balance, fullname, currency) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, iban);
                ps.setString(2, account.getAccounttype());
                ps.setString(3, user.getUsername());
                ps.setDouble(4, account.getBalance());
                ps.setString(5, user.getFullname());
                ps.setString(6, currency.name());

                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Failed to initialize currency account: " + e.getMessage());
            }
        }
    }

    public static boolean doesCurrencyAccountExist(String username, String currency) {
        String sql = "SELECT 1 FROM accounts WHERE username = ? AND currency = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, currency);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static CurrencyCashOutAccount getCurrencyCashOutAccount(User user, Currency currency) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND accounttype = 'currency' AND currency = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, currency.name());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                return new CurrencyCashOutAccount(currency, balance);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching currency cash out account: " + e.getMessage());
        }
        return null;
    }
    public static Accounts findMDLAccount(User user) {
        Collection<Accounts> allAccounts = AccountsRepository.getAllAccountsForUser(user.getUsername());
        for (Accounts account : allAccounts) {
            String type = account.getAccounttype().toLowerCase();
            if (type.equals("general") || type.equals("savings") || type.equals("card")|| type.equals("currency")) {
                return account;
            }
        }
        System.out.println("No MDL account found for user: " + user.getUsername());
        return null;
    }
}
