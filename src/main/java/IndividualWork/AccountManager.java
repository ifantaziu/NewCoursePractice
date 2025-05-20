package IndividualWork;

import java.util.*;

public class AccountManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static User user;

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getChoice();
            try {
                switch (choice) {
                    case 1 -> onboardNewUser();
                    case 2 -> openNewAccount();
                    case 3 -> transferBetweenAccounts();
                    case 4 -> deposit();
                    case 5 -> withdrawal();
                    case 6 -> makePayment();
                    case 7 -> applyInterest();
                    case 8 -> initiateCurrencyExchange();
                    case 9 -> withdrawFromCashOutAccount();
                    case 10 -> exit();
                    default -> System.out.println("Invalid choice, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Account Manager Menu ---");
        System.out.println("1. Onboard New User");
        System.out.println("2. Open a New Account");
        System.out.println("3. Transfer Between Own Accounts");
        System.out.println("4. Deposit to Account");
        System.out.println("5. Withdraw from Account");
        System.out.println("6. Make Payment (Card Account only)");
        System.out.println("7. Apply Interest (Savings Account only)");
        System.out.println("8. Currency Exchange");
        System.out.println("9. Withdraw from Cash-Out Account");
        System.out.println("10. Exit");
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
        user = Onboarding.onboardNewUser();
        AccountStorage.initializeCurrencyAccount(user);
        System.out.println("New user onboarded: " + user);
    }

    private static void openNewAccount() {
        if (user == null) {
            System.out.println("Please onboard a user first (option 1).");
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
            case 1 -> new GeneralAccount(null, user.getFullName(), initialDeposit);
            case 2 -> new SavingsAccount(null, user.getFullName(), initialDeposit);
            case 3 -> {
                System.out.println("Do you want to issue a plastic card? (yes/no)");
                String answer = scanner.nextLine().trim().toLowerCase();
                String deliveryAddress = null;
                if (answer.equals("yes")) {
                    System.out.println("Please enter your delivery address:");
                    deliveryAddress = scanner.nextLine();
                    System.out.println("Thank you! The courier will call you within three days for card delivery.");
                }
                yield new CardAccount(null, user.getFullName(), initialDeposit, deliveryAddress);
            }
            default -> {
                System.out.println("Invalid account type.");
                yield null;
            }
        };

        if (account != null) {
            AccountStorage.addAccount(user, account);  // <-- Aici
            System.out.println("Account created successfully:");
            account.displayAccountDetails();
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
        account.withdrawal(amount);
    }

    private static Accounts findAccountByIban(String iban) {
        try {
            return AccountStorage.findAccountByIban(user, iban);
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

        CurrencyCashOutAccount cashOutAccount = AccountStorage.getCurrencyCashOutAccount(user, currency);
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
        } catch (Exception e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    private static void initiateCurrencyExchange() {
        System.out.println("Available currencies:");
        for (Currency currency : Currency.values()) {
            System.out.printf("%s: %.2f MDL%n", currency, currency.getExchangeRate());
        }

        System.out.print("Select currency to convert FROM (e.g. USD): ");
        Currency fromCurrency = Currency.valueOf(scanner.nextLine().toUpperCase());

        convertFromCurrencyToMDL(user, fromCurrency, scanner);
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

    }

    private static void transferBetweenAccounts() {
        System.out.println("Transfer between accounts is not implemented yet.");
    }

    private static void exit() {
        System.out.println("Exiting the Account Manager. Goodbye!");
        System.exit(0);
    }


}



