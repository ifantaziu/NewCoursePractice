package IndividualWork;

public class GeneralAccount extends AbstractAccount implements Accounts {
    private static int generalCounter = 1000;
    private final String iban;
    private final String accountHolderName;

    public GeneralAccount(String iban, String accountHolderName, double balance) {
        super(balance);
        this.iban = generateIban();
        this.accountHolderName = accountHolderName;
    }

    @Override
    public String generateIban() {
        generalCounter++;
        return String.format("GEN2251-%06d", generalCounter);
    }

    @Override
    public String getIban() {
        return iban;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }


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
