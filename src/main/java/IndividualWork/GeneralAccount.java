package IndividualWork;

public class GeneralAccount implements Accounts {
    private static int generalCounter = 1000;
    public double balance = 0.0;
    private final String iban;
    private final String accountHolderName;

    public GeneralAccount(String iban, String accountHolderName, double balance) {
        this.iban = generateIban();
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    @Override
    public String generateIban() {
        generalCounter++;
        return String.format("MDA00GEN2251-%06d", generalCounter);
    }

    public double getBalance() {
        return balance;
    }

    public String getIban() {
        return iban;
    }

    public String getAccountHolderName() {
        return accountHolderName;
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
                throw new InvalidAmountException("Invalid withdrawal amount.");
            }
            if (amount > balance) {
                throw new InsufficientFundsException("Not enough funds in general account.");
            }
            balance -= amount;
            System.out.printf("Withdrew successful%.2f MDL. Remaining balance: %.2f MDL%n", amount, balance);
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

        @Override
        public void checkAccountBalance () {
            System.out.println("The balance on your account #" + getIban() + " is: " + getBalance() +
                    " MDL ");
        }

        @Override
        public void transferBetweenOwnAccounts ( double amount, Accounts destinationAccount) throws InvalidAmountException {
            if (amount <= 0 || amount > balance) {
                System.out.println("Transfer failed: invalid amount or insufficient funds.");
                return;
            }

            this.balance -= amount;
            destinationAccount.deposit(amount);
            System.out.printf("Transferred %.2f MDL from General Account to target account.%n", amount);
        }

        public void displayAccountDetails () {
            System.out.println("Account details ");
            System.out.println("Account with IBAN: " + getIban());
            System.out.println("Account holder name is: " + getAccountHolderName());
            System.out.println("General account balance: " + getBalance() + " MDL");
            System.out.println();
        }
    }
