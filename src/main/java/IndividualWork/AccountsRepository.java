package IndividualWork;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class AccountsRepository {

    private static final Connection connection = DBConnect.getConnection();

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

        String sql = """
                INSERT INTO accounts 
                (iban, accounttype, username, balance, fullname, issuecard, deliveryaddress, account_opening_date, last_interest_date) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, iban);
            ps.setString(2, account.getAccounttype());
            ps.setString(3, user.getUsername());
            ps.setDouble(4, account.getBalance());
            ps.setString(5, user.getFullname());

            // Card specific
            if (account instanceof CardAccount card) {
                ps.setBoolean(6, true);
                ps.setString(7, card.getCardDeliveryAddress());
            } else {
                ps.setBoolean(6, false);
                ps.setString(7, null);
            }

            // Savings specific
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

    public static Collection<Accounts> getAllAccountsForUser(String fullname) {
        Collection<Accounts> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE fullname = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fullname);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("account_type").toLowerCase();
                String iban = rs.getString("iban");
                double balance = rs.getDouble("balance");

                switch (type) {
                    case "card" -> {
                        String deliveryAddress = rs.getString("card_delivery_address");
                        CardAccount card = new CardAccount(iban, fullname, balance, deliveryAddress);
                        accounts.add(card);
                    }
                    case "general" -> {
                        GeneralAccount general = new GeneralAccount(iban, fullname, balance);
                        accounts.add(general);
                    }
                    case "savings" -> {
                        LocalDate openingDate = rs.getDate("account_opening_date").toLocalDate();
                        LocalDate lastInterestDate = rs.getDate("last_interest_date").toLocalDate();
                        SavingsAccount savings = new SavingsAccount(iban, fullname, balance);
                        // Suprascriem datele reale
                        savings.setAccountOpeningDate(openingDate);
                        savings.setLastInterestDate(lastInterestDate);
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


    // Similar poți adăuga metode: getAllGeneralAccounts(), getAllSavingsAccounts()

}
