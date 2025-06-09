package IndividualWork;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

public abstract class AbstractAccount implements Accounts {
    private double balance;
    String iban;

    public void deposit(double amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Deposit must be greater than zero.");
            }
            setBalance(getBalance() + amount);
            System.out.printf("Deposited successful %.2f MDL. New balance: %.2f MDL%n", amount, balance);
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void withdrawal(double amount) throws InvalidAmountException, InsufficientFundsException {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Invalid withdrawal amount.");
            }
            if (amount > balance) {
                throw new InsufficientFundsException("Not enough funds in card account.");
            }
            setBalance(getBalance() - amount);
            System.out.printf("Withdrew successful %.2f MDL. Remaining balance: %.2f MDL%n", amount, balance);
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void checkAccountBalance() {
        System.out.printf("Current balance: %.2f MDL%n", balance);
    }
}
