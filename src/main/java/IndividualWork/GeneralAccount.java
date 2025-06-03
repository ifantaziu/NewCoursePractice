package IndividualWork;

public class GeneralAccount extends AbstractAccount implements Accounts {
<<<<<<< HEAD
    private static int generalCounter = 1000;
=======
>>>>>>> a491eab (Save local changes before rebase)
    private final String iban;
    private final String accountHolderName;

    public GeneralAccount(String iban, String accountHolderName, double balance) {
<<<<<<< HEAD
        super(balance);
        this.iban = generateIban();
=======
        super(balance, iban);
        this.iban = IdGenerator.generateIban("general");
>>>>>>> a491eab (Save local changes before rebase)
        this.accountHolderName = accountHolderName;
    }

    @Override
<<<<<<< HEAD
    public String generateIban() {
        generalCounter++;
        return String.format("GEN2251-%06d", generalCounter);
    }

    @Override
=======
>>>>>>> a491eab (Save local changes before rebase)
    public String getIban() {
        return iban;
    }

<<<<<<< HEAD
=======
    @Override
    public String getAccountType() {
        return "general";
    }

>>>>>>> a491eab (Save local changes before rebase)
    public String getAccountHolderName() {
        return accountHolderName;
    }


<<<<<<< HEAD
        @Override
        public void transferBetweenOwnAccounts ( double amount, Accounts destinationAccount) throws InvalidAmountException {
            if (amount <= 0 || amount > getBalance()) {
                System.out.println("Transfer failed: invalid amount or insufficient funds.");
                return;
            }

            double balance= getBalance() - amount;
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
=======
    @Override
    public void transferBetweenOwnAccounts(double amount, Accounts destinationAccount) throws InvalidAmountException {
        if (amount <= 0 || amount > getBalance()) {
            System.out.println("Transfer failed: invalid amount or insufficient funds.");
            return;
        }

        double balance = getBalance() - amount;
        destinationAccount.deposit(amount);
        System.out.printf("Transferred %.2f MDL from General Account to target account.%n", amount);
    }

    public void displayAccountDetails() {
        System.out.println("Account details ");
        System.out.println("Account with IBAN: " + getIban());
        System.out.println("Account holder name is: " + getAccountHolderName());
        System.out.println("General account balance: " + getBalance() + " MDL");
        System.out.println();
    }
}
>>>>>>> a491eab (Save local changes before rebase)
