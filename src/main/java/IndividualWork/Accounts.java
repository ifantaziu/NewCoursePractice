package IndividualWork;

public interface Accounts {

    void deposit(double amount)throws InvalidAmountException;

    void withdrawal(double amount)throws InvalidAmountException, InsufficientFundsException, AccountNotMatureException;

    void checkAccountBalance();

    void displayAccountDetails();

    void transferBetweenOwnAccounts(double amount, Accounts destinationAccount) throws InvalidAmountException,
            InsufficientFundsException, AccountNotMatureException;

    String generateIban();
    String getIban();
}
