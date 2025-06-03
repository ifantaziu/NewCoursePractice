package IndividualWork;


public class CurrencyCashOutAccount {
    private Currency currency;
    private double balance;

    public CurrencyCashOutAccount(Currency currency) {
        this.currency = currency;
        this.balance = 0.0;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.printf("Deposited %.2f %s. New balance: %.2f %s%n", amount, currency, balance, currency);
    }

    public void withdrawal(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }
        if (amount % 5 != 0) {
            throw new InvalidAmountException("Withdrawal amount must be divisible by 5.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough funds in currency cash-out account.");
        }
        balance -= amount;
        System.out.printf("Withdrew %.2f %s. Remaining balance: %.2f %s%n", amount, currency, balance, currency);
    }

    public double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void displayAccountDetails() {
        System.out.println("=== Currency Cash-Out Account Details ===");
        System.out.println("Currency: " + currency);
        System.out.printf("Balance: %.2f %s%n", balance, currency);
    }
}

