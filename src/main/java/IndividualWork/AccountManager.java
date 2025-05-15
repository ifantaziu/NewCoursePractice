package IndividualWork;

import java.util.Scanner;

public class AccountManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getChoice();
            try {
                switch (choice) {
                    case 1:
                        onboardNewUser();
                        break;
                    case 2:
                        openNewAccount();
                        break;
                    case 3:
                        transferBetweenAccounts();
                        break;
                    case 4:
                        exit();
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
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
        System.out.println("4. Exit");
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
        User user = Onboarding.onboardNewUser();
        System.out.println("\nNew user onboarded successfully: ");
        System.out.println(user);
    }

    private static void openNewAccount() {
        System.out.println("\nChoose account type:");
        System.out.println("1. General Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Card Account");
        System.out.print("Enter your choice: ");

        int accountType = getChoice();
        if (accountType < 1 || accountType > 3) {
            System.out.println("Invalid account type.");
            return;
        }

        System.out.print("Enter the account holder's name: ");
        String accountHolderName = scanner.nextLine();

        System.out.print("Enter the initial deposit amount: ");
        double initialDeposit = Double.parseDouble(scanner.nextLine());

        Accounts account = null;
        switch (accountType) {
            case 1:
                account = new GeneralAccount(null, accountHolderName, initialDeposit);  // IBAN is auto-generated
                break;
            case 2:
                account = new SavingsAccount(null, accountHolderName, initialDeposit);  // IBAN is auto-generated
                break;
            case 3:
                account = new CardAccount(null, accountHolderName, initialDeposit);  // IBAN is auto-generated
                break;
        }

        System.out.println("Account created successfully!");
        account.displayAccountDetails();
    }

    private static void transferBetweenAccounts() {
        System.out.print("Enter the source account IBAN: ");
        System.out.print("Enter the amount to transfer: ");
    }

    private static void exit() {
        System.out.println("Exiting the Account Manager.");
    }
}


