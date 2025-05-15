package IndividualWork;

public class CardAccount implements Accounts {
    private static int cardCounter = 3000;
    private final String iban;
    private final String accountHolderName;
    private double balance;

    public CardAccount(String iban, String accountHolderName, double initialBalance) {
        this.iban = generateIban();
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    @Override
    public String generateIban() {
        cardCounter++;
        return String.format("MDA00CARD2259-%06d", cardCounter);
    }

    public void makePayment(double amount, String merchant) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Invalid payment amount.");
            }
            if (amount > balance) {
                throw new InsufficientFundsException("Insufficient funds for payment.");
            }
            balance -= amount;
            System.out.printf("Paid successful %.2f MDL to %s. Remaining balance: %.2f MDL%n", amount, merchant, balance);
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deposit(double amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Deposit must be greater than zero.");
            }
            balance += amount;
            System.out.printf("Deposited successful %.2f MDL. New balance: %.2f MDL%n", amount, balance);
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void withdrawal(double amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Invalid withdrawal amount.");
            }
            if (amount > balance) {
                throw new InsufficientFundsException("Not enough funds in card account.");
            }
            balance -= amount;
            System.out.printf("Withdrew successful %.2f MDL. Remaining balance: %.2f MDL%n", amount, balance);
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void checkAccountBalance() {
        System.out.printf("Current balance: %.2f MDL%n", balance);
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("=== Card Account Details ===");
        System.out.println("IBAN: " + iban);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.printf("Balance: %.2f MDL%n", balance);
    }

    @Override
    public void transferBetweenOwnAccounts(double amount, Accounts destinationAccount) throws InvalidAmountException {
        if (amount <= 0 || amount > balance) {
            System.out.println("Transfer failed: invalid amount or insufficient funds.");
            return;
        }

        this.balance -= amount;
        destinationAccount.deposit(amount);

        System.out.printf("Transferred %.2f MDL from Card Account to target account.%n", amount);
    }
}
