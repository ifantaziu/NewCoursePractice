package IndividualWork;

import lombok.Getter;

@Getter
public class GeneralAccount extends AbstractAccount implements Accounts {
    private final String iban;
    private final String fullname;

    public GeneralAccount(String iban, String fullname, double balance) {
        super(balance, iban);
        this.iban = IdGenerator.generateIban("general");
        this.fullname = fullname;
    }

    @Override
    public String getIban() {
        return iban;
    }

    @Override
    public String getAccounttype() {
        return "general";
    }

    public String getFullname() {
        return fullname;
    }


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
        System.out.println("Account holder name is: " + getFullname());
        System.out.println("General account balance: " + getBalance() + " MDL");
        System.out.println();
    }
}
