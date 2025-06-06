package IndividualWork;

import lombok.Getter;

@Getter
public class CurrencyCashOutAccount extends AbstractAccount implements Accounts{
    private Currency currency;

    public CurrencyCashOutAccount(Currency currency, double balance) {
        super(balance, IdGenerator.generateIban("currency"));
        this.currency = currency;
    }

      @Override
    public void withdrawal(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }
        if (amount % 5 != 0) {
            throw new InvalidAmountException("Withdrawal amount must be divisible by 5.");
        }
        if (amount > getBalance()) {
            throw new InsufficientFundsException("Not enough funds in currency cash-out account.");
        }
        setBalance(getBalance() - amount);
        System.out.printf("Withdrew %.2f %s. Remaining balance: %.2f %s%n", amount, currency, getBalance(), currency);
    }


    public void displayAccountDetails() {
        System.out.println("=== Currency Cash-Out Account Details ===");
        System.out.println("Currency: " + currency);
        System.out.printf("Balance: %.2f %s%n", getBalance(), currency);
    }

    @Override
    public void transferBetweenOwnAccounts(double amount, Accounts destinationAccount) throws InvalidAmountException, InsufficientFundsException, AccountNotMatureException {

    }

    @Override
    public String getAccounttype() {
        return "cashout account";
    }
}

