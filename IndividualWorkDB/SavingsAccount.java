`package IndividualWork;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SavingsAccount extends AbstractAccount implements Accounts {
    private static int savingsCounter = 2000;
    private static final int maturityTermMonths = 6;
    private final String iban;
    private final String accountHolderName;
    private final LocalDate accountOpeningDate;
    private LocalDate lastInterestDate;

    private static final double ANNUAL_INTEREST_RATE = 0.065;

    public SavingsAccount(String iban, String accountHolderName, double balance) {
        super(balance, iban);
        this.iban = IdGenerator.generateIban("savings");
        this.accountHolderName = accountHolderName;
        this.accountOpeningDate = LocalDate.now();
        this.lastInterestDate = this.accountOpeningDate;
    }


    @Override
    public String getIban() {
        return iban;
    }

    @Override
    public String getAccountType() {
        return "savings";
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
            double interest = getBalance() * dailyRate;
            double balance = getBalance() + interest;
        }

        lastInterestDate = today;

        System.out.printf("Applied and capitalized interest for %d day(s). New balance: %.2f MDL%n", days, getBalance());
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
            if (amount > getBalance()) {
                throw new InsufficientFundsException("Not enough funds in savings account.");
            }
            double balance = getBalance() - amount;
            System.out.printf("Withdrew successful %.2f MDL. Remaining balance: %.2f MDL%n", amount, getBalance());
        } catch (InvalidAmountException | InsufficientFundsException | AccountNotMatureException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void transferBetweenOwnAccounts(double amount, Accounts destinationAccount) throws InvalidAmountException {
        if (amount <= 0 || amount > getBalance()) {
            System.out.println("Transfer failed: invalid amount or insufficient funds.");
            return;
        }

        withdrawal(amount);
        destinationAccount.deposit(amount);

        System.out.printf("Transferred %.2f MDL from Card Account to target account.%n", amount);
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("=== Savings Account Details ===");
        System.out.println("IBAN: " + iban);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Opened on: " + accountOpeningDate);
        System.out.printf("Balance: %.2f MDL%n", getBalance());
        System.out.println("Last interest date: " + lastInterestDate);
    }
}
`