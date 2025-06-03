package IndividualWork;

public interface Accounts {

<<<<<<< HEAD
    void deposit(double amount)throws InvalidAmountException;

    void withdrawal(double amount)throws InvalidAmountException, InsufficientFundsException, AccountNotMatureException;
=======
    void deposit(double amount) throws InvalidAmountException;

    void withdrawal(double amount) throws InvalidAmountException, InsufficientFundsException, AccountNotMatureException;
>>>>>>> a491eab (Save local changes before rebase)

    void checkAccountBalance();

    void displayAccountDetails();

    void transferBetweenOwnAccounts(double amount, Accounts destinationAccount) throws InvalidAmountException,
            InsufficientFundsException, AccountNotMatureException;

<<<<<<< HEAD
    String generateIban();
    String getIban();
=======

    String getIban();

    String[] accountType = {"card", "general", "savings"};

    String getAccountType();

    double getBalance();
>>>>>>> a491eab (Save local changes before rebase)
}
