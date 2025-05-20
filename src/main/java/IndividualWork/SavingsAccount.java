package IndividualWork;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SavingsAccount implements Accounts {
    private static int savingsCounter = 2000;
    private final String iban;
    private final String accountHolderName;
    private double balance;
    private final LocalDate accountOpeningDate;
    private LocalDate lastInterestDate;

    private static final double ANNUAL_INTEREST_RATE = 0.065;

    public SavingsAccount(String iban, String accountHolderName, double initialDeposit) {
        this.iban = generateIban();
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountOpeningDate = LocalDate.now();
        this.lastInterestDate = this.accountOpeningDate;
    }

    @Override
    public String generateIban() {
        savingsCounter++;
        return String.format("MDA00SAV2252-%06d", savingsCounter);
    }
    @Override
    public String getIban() {
        return iban;
    }

    public void applyDailyInterestAndCapitalize() {
        LocalDate today = LocalDate.now();
        long days = ChronoUnit.DAYS.between(lastInterestDate, today);

        if (days <= 0) {
            System.out.println("No new interest to apply today.");
            return;
        }

        double dailyRate = ANNUAL_INTEREST_RATE / 365;

        for (int i = 0; i < days; i++) {
            double interest = balance * dailyRate;
            balance += interest;
        }

        lastInterestDate = today;

        System.out.printf("Applied and capitalized interest for %d day(s). New balance: %.2f MDL%n", days, balance);
    }

    @Override
    public void deposit(double amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Deposit must be greater than zero.");
            }
            balance += amount;
            System.out.printf("Deposited successful%.2f MDL. New balance: %.2f MDL%n", amount, balance);
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void withdrawal(double amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
            }
            if (ChronoUnit.MONTHS.between(accountOpeningDate, LocalDate.now()) < 6) {
                throw new AccountNotMatureException("Cannot withdraw before account matures (6 months).");
            }
            if (amount > balance) {
                throw new InsufficientFundsException("Not enough funds in savings account.");
            }
            balance -= amount;
            System.out.printf("Withdrew successful %.2f MDL. Remaining balance: %.2f MDL%n", amount, balance);
        } catch (InvalidAmountException | InsufficientFundsException | AccountNotMatureException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void checkAccountBalance() {
        System.out.printf("Current balance: %.2f MDL%n", balance);
    }

    @Override
    public void transferBetweenOwnAccounts(double amount, Accounts destinationAccount) throws InvalidAmountException {
        LocalDate today = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(accountOpeningDate, today);

        if (months < 6) {
            System.out.println("Transfer not allowed: Savings account is not yet mature.");
            return;
        }

        if (amount <= 0 || amount > balance) {
            System.out.println("Transfer failed: insufficient funds.");
            return;
        }

        this.balance -= amount;
        destinationAccount.deposit(amount);
        System.out.printf("Transferred %.2f MDL from Savings Account to target account.%n", amount);
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("=== Savings Account Details ===");
        System.out.println("IBAN: " + iban);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Opened on: " + accountOpeningDate);
        System.out.printf("Balance: %.2f MDL%n", balance);
        System.out.println("Last interest date: " + lastInterestDate);
    }
}
