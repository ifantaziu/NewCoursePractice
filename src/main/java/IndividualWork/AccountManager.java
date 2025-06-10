package IndividualWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;

import static IndividualWork.AccountsRepository.findMDLAccount;

public class AccountManager {
    private static final Logger logger = LoggerFactory.getLogger(AccountManager.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    private static Onboarding onboarding;
    private static AccountsRepository accountsRepository;
    private static TransactionsRepository transactionsRepository;


    public static void main(String[] args) throws SQLException {
        {
            Connection connection = new DBConnect().getConnection();
            onboarding = new Onboarding(connection);
            accountsRepository = new AccountsRepository(connection);
            transactionsRepository = new TransactionsRepository(connection);

            while (true) {
                showMenu();
                int choice = getChoice();
                try {
                    switch (choice) {
                        case 0 -> onboardNewUser();
                        case 1 -> loginUser();
                        case 2 -> openNewAccount();
                        case 3 -> transferBetweenAccounts();
                        case 4 -> deposit();
                        case 5 -> withdrawal();
                        case 6 -> makePayment();
                        case 7 -> applyInterest();
                        case 8 -> initiateCurrencyExchange();
                        case 9 -> withdrawFromCashOutAccount();
                        case 10 -> showAccountList();
                        case 11 -> getTransactionsReport();
                        case 12 -> removeAnAccount();
                        case 13 -> {
                            connection.close();
                            exit();
                        }
                        default -> System.out.println("Invalid choice, please try again.");
                    }
                } catch (Exception e) {
                    System.err.println("Error while processing request: " + e.getMessage());
                }
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Account Manager Menu ---");
        System.out.println("0. Onboard New User");
        System.out.println("1. Login");
        System.out.println("2. Open a New Account");
        System.out.println("3. Transfer Between Own Accounts");
        System.out.println("4. Deposit to Account");
        System.out.println("5. Withdraw from Account");
        System.out.println("6. Make Payment (Card Account only)");
        System.out.println("7. Apply Interest (Savings Account only)");
        System.out.println("8. Currency Exchange");
        System.out.println("9. Withdraw from Cash-Out Account");
        System.out.println("10. Show my Accounts");
        System.out.println("11. Show transactions Report");
        System.out.println("12. Remove an Account");
        System.out.println("13. Exit");
        System.out.print("Please enter your choice: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    private static void onboardNewUser() {
        currentUser = onboarding.onboardNewUser();
        if (currentUser != null) {
            logger.info("New user onboarded:\n" + currentUser);
        } else {
            logger.error("User onboarding failed.");
        }
    }

    private static void loginUser() {
        currentUser = onboarding.loginUser();
        if (currentUser != null) {
            System.out.println("You are logged in as: " + currentUser.getFullname());
        }
    }

    private static void openNewAccount() {
        if (currentUser == null) {

            System.out.println("Please onboard a user first (option 0).");
            return;
        }

        System.out.println("\nChoose account type:");
        System.out.println("1. General Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Card Account");
        System.out.print("Enter your choice: ");
        int accountType = getChoice();

        System.out.print("Enter the initial deposit amount: ");
        double initialDeposit = Double.parseDouble(scanner.nextLine());

        Accounts account = switch (accountType) {
            case 1 -> new GeneralAccount(null, currentUser.getFullname(), initialDeposit);
            case 2 -> new SavingsAccount(null, currentUser.getFullname(), initialDeposit);
            case 3 -> {
                System.out.println("Do you want to issue a plastic card? (yes/no)");
                String answer = scanner.nextLine().trim().toLowerCase();
                String deliveryAddress = null;
                if (answer.equals("yes")) {
                    System.out.println("Please enter your delivery address:");
                    deliveryAddress = scanner.nextLine();
                    System.out.println("Thank you! The courier will call you within three days for card delivery.");
                }
                yield new CardAccount(null, currentUser.getFullname(), initialDeposit, deliveryAddress);
            }
            default -> {
                System.out.println("Invalid account type.");
                yield null;
            }
        };

        if (account != null) {
            accountsRepository.addAccount(currentUser, account);
            // System.out.println("Account created successfully:");
            logger.info("Account created successfully:");
            account.displayAccountDetails();
        }
    }

    private static void showAccountList() {
        Collection<Accounts> accounts = AccountsRepository.getAllAccountsForUser(currentUser.getUsername());

        for (Accounts account : accounts) {
            System.out.println(account); // poți personaliza afisajul
        }
    }

    private static void getTransactionsReport() {

        while (true) {
            System.out.println("\nChoose how do you need the Report to be generated:");
            System.out.println("\ntype 'IBAN' if you need a report for a specific Account");
            System.out.println("\ntype 'USER' if you need a report for all your Accounts\"");
            System.out.print("\nor type EXIT to return to Account Manager Menu): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("EXIT")) {
                System.out.println("Returning to main menu...");
                break;
            } else if (input.equals("IBAN")) {
                System.out.print("Enter the account IBAN: ");
                String iban = scanner.nextLine();
                Accounts account = findAccountByIban(iban);
                if (account == null) {
                    System.out.println("Account not found.");
                    return;
                }
                TransactionsRepository.getTransactionsByIban(iban);
            } else if (input.equals("USER")) {
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                User user = UserRepository.getUserByUsername(username);
                if (user == null) {
                    System.out.println("User not found.");
                    return;
                }
                TransactionsRepository.getTransactionsByUser(username);
            }

        }
    }

    private static void makePayment() {
        System.out.print("Enter the Card Account IBAN: ");
        String iban = scanner.nextLine();
        Accounts account = findAccountByIban(iban);
        if (account instanceof CardAccount cardAccount) {
            System.out.print("Enter the payment amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter the merchant name: ");
            String merchant = scanner.nextLine();
            cardAccount.makePayment(amount, merchant);
            AccountsRepository.updateAccountBalance(iban, cardAccount.getBalance());
            TransactionsRepository.recordTransaction(
                    currentUser.getUsername(),
                    iban,
                    null,
                    "payment",
                    amount,
                    "MDL",
                    Timestamp.valueOf(LocalDateTime.now()),
                    "Payment to merchant: " + merchant
            );
        } else {
            System.out.println("Error: The specified IBAN does not belong to a Card Account.");
        }
    }


    private static void applyInterest() {
        System.out.print("Enter the Savings Account IBAN: ");
        String iban = scanner.nextLine();
        Accounts account = findAccountByIban(iban);
        if (account instanceof SavingsAccount savingsAccount) {
            savingsAccount.applyDailyInterestAndCapitalize();

            AccountsRepository.updateAccountBalance(iban, savingsAccount.getBalance());

            TransactionsRepository.recordTransaction(
                    currentUser.getUsername(),
                    null,
                    iban,
                    "interest",
                    savingsAccount.getLastInterestApplied(),
                    "MDL",
                    Timestamp.valueOf(LocalDateTime.now()),
                    "Daily interest capitalization"
            );

        } else {
            System.out.println("Error: The specified IBAN does not belong to a Savings Account.");
        }
    }

    private static void deposit() throws InvalidAmountException {
        System.out.print("Enter the account IBAN: ");
        String iban = scanner.nextLine();
        Accounts account = findAccountByIban(iban);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.print("Enter the amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        account.deposit(amount);

        AccountsRepository.updateAccountBalance(iban, account.getBalance());
        TransactionsRepository.recordTransaction(
                currentUser.getUsername(),
                null,
                iban,
                "deposit",
                amount,
                "MDL",
                Timestamp.valueOf(LocalDateTime.now()),
                "Deposit"
        );
    }

    private static void withdrawal() throws InvalidAmountException, InsufficientFundsException, AccountNotMatureException {
        System.out.print("Enter the account IBAN: ");
        String iban = scanner.nextLine();
        Accounts account = findAccountByIban(iban);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.print("Enter the amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        try {
            if (account instanceof SavingsAccount savingsAccount) {
                savingsAccount.withdrawal(amount);
            } else {
                account.withdrawal(amount);
            }
            AccountsRepository.updateAccountBalance(iban, account.getBalance());
            TransactionsRepository.recordTransaction(
                    currentUser.getUsername(),
                    iban,
                    null,
                    "withdrawal",
                    amount,
                    "MDL",
                    Timestamp.valueOf(LocalDateTime.now()),
                    "Withdrawal"
            );
        } catch (InvalidAmountException | InsufficientFundsException | AccountNotMatureException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Accounts findAccountByIban(String iban) {
        try {
            return AccountsRepository.findAccountByIban(iban);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void withdrawFromCashOutAccount() {
        System.out.print("Enter currency (e.g., EUR, USD, GBP, RON): ");
        Currency currency;
        try {
            currency = Currency.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid currency.");
            return;
        }
        CurrencyCashOutAccount cashOutAccount = AccountsRepository.getCurrencyCashOutAccount(currentUser, currency);
        if (cashOutAccount == null) {
            System.out.println("Cash-out account not found.");
            return;
        }
        System.out.print("Enter the amount to withdraw (multiple of 5): ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (amount % 5 != 0) {
            System.out.println("You can only withdraw sums divisible by 5.");
            return;
        }
        try {
            cashOutAccount.withdrawal(amount);
            AccountsRepository.updateAccountBalance(cashOutAccount.getIban(), cashOutAccount.getBalance());
            TransactionsRepository.recordTransaction(
                    currentUser.getUsername(),
                    cashOutAccount.getIban(),
                    null,
                    "cashout",
                    amount,
                    currency.name(),
                    Timestamp.valueOf(LocalDateTime.now()),
                    "Cash-out withdrawal in " + currency.name()
            );
        } catch (Exception e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }


    private static void initiateCurrencyExchange() {
        System.out.println("Available currencies:");
        for (Currency currency : Currency.values()) {
            System.out.printf("%s: %.2f MDL%n", currency, currency.getExchangeRate());
        }


        AccountsRepository.initializeCurrencyAccounts(currentUser);

        String answer;
        do {
            System.out.print("Select currency to convert FROM (e.g. USD): ");
            Currency fromCurrency = Currency.valueOf(scanner.nextLine().toUpperCase());

            convertFromCurrencyToMDL(currentUser, fromCurrency, scanner);

            System.out.print("Do you want to convert another currency? (yes/no): ");
            answer = scanner.nextLine().trim().toLowerCase();
        } while (answer.equals("yes"));
    }

    public static void convertFromCurrencyToMDL(User user, Currency fromCurrency, Scanner scanner) {
        System.out.printf("Current exchange rate: 1 %s = %.2f MDL%n", fromCurrency, fromCurrency.getExchangeRate());
        System.out.print("Enter the amount in " + fromCurrency + " to convert: ");
        double amountInCurrency = scanner.nextDouble();
        scanner.nextLine();
        if (amountInCurrency % 5 != 0) {
            System.out.println("The amount must be divisible by 5 to proceed with cash-out.");
            return;
        }
        double convertedAmountMDL = fromCurrency.convertToMDL(amountInCurrency);
        System.out.printf("%.2f %s was successfully converted to %.2f MDL.%n",
                amountInCurrency, fromCurrency, convertedAmountMDL);
        CurrencyCashOutAccount fromAccount = AccountsRepository.getCurrencyCashOutAccount(user, fromCurrency);
        Accounts mdlAccount = findMDLAccount(user);
        if (fromAccount != null && mdlAccount != null) {
            try {
                fromAccount.withdrawal(amountInCurrency);
                mdlAccount.deposit(convertedAmountMDL);
                AccountsRepository.updateAccountBalance(fromAccount.getIban(), fromAccount.getBalance());
                AccountsRepository.updateAccountBalance(mdlAccount.getIban(), mdlAccount.getBalance());
                TransactionsRepository.recordTransaction(
                        user.getUsername(),
                        fromAccount.getIban(),
                        mdlAccount.getIban(),
                        "currency-exchange",
                        convertedAmountMDL,
                        "MDL",
                        Timestamp.valueOf(LocalDateTime.now()),
                        String.format("Converted %.2f %s to %.2f MDL", amountInCurrency, fromCurrency.name(), convertedAmountMDL)
                );
            } catch (Exception e) {
                System.out.println("Currency exchange failed: " + e.getMessage());
            }
        }
    }

    private static void transferBetweenAccounts() {
        if (currentUser == null) {
            System.out.println("Please onboard a user first (option 1).");
            return;
        }
        Accounts sourceAccount = null;
        while (sourceAccount == null) {
            System.out.print("Enter source account IBAN: ");
            String sourceIban = scanner.nextLine();
            sourceAccount = findAccountByIban(sourceIban);
            if (sourceAccount == null) {
                System.out.println("Source account not found. Please try again.");
            }
        }
        Accounts destinationAccount = null;
        while (destinationAccount == null) {
            System.out.print("Enter destination account IBAN: ");
            String destIban = scanner.nextLine();
            destinationAccount = findAccountByIban(destIban);
            if (destinationAccount == null) {
                System.out.println("Destination account not found. Please try again.");
            }
        }
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Enter amount to transfer: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("Amount must be greater than zero. Please try again.");
                } else {
                    validAmount = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount format. Please enter a number.");
            }
        }
        try {
            sourceAccount.transferBetweenOwnAccounts(amount, destinationAccount);
            AccountsRepository.updateAccountBalance(sourceAccount.getIban(), sourceAccount.getBalance());
            AccountsRepository.updateAccountBalance(destinationAccount.getIban(), destinationAccount.getBalance());
            System.out.println("Updating source account balance for IBAN: " + sourceAccount.getIban() + " with new balance: " + sourceAccount.getBalance());
            System.out.println("Updating destination account balance for IBAN: " + destinationAccount.getIban() + " with new balance: " + destinationAccount.getBalance());
            TransactionsRepository.recordTransaction(
                    currentUser.getUsername(),
                    sourceAccount.getIban(),
                    destinationAccount.getIban(),
                    "internal-transfer",
                    amount,
                    "MDL",
                    Timestamp.valueOf(LocalDateTime.now()),
                    "Transfer between own accounts"
            );

        } catch (InvalidAmountException | InsufficientFundsException | AccountNotMatureException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }


    private static void removeAnAccount() {
        System.out.print("Enter the account IBAN: ");
        String iban = scanner.nextLine();
        AccountsRepository.deleteAccount(iban);
    }

    private static void exit() {
        System.out.println("Exiting the Account Manager. Goodbye!");
        System.exit(0);
    }
}
