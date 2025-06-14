package IndividualWork;

import lombok.Getter;

@Getter
public class CardAccount extends AbstractAccount implements Accounts {
    private final String iban;
    private final String fullname;
    private String carddeliveryaddress = null;

    public CardAccount(String iban, String fullname, double balance, String carddeliveryaddress) {
        super(balance, iban);
        this.iban = IdGenerator.generateIban("card");
        this.fullname = fullname;
        this.carddeliveryaddress = carddeliveryaddress;
    }


    @Override
    public String getIban() {
        return iban;
    }

    @Override
    public String getAccounttype() {
        return "card";
    }

    public String getCarddeliveryaddress() {
        return carddeliveryaddress;
    }

    public void makePayment(double amount, String merchant) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Invalid payment amount.");
            }
            if (amount > getBalance()) {
                throw new InsufficientFundsException("Insufficient funds for payment.");
            }

            setBalance(getBalance() - amount);
            System.out.printf("Paid successfully %.2f MDL to %s. Remaining balance: %.2f MDL%n",
                    amount, merchant, getBalance());
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Override
    public void displayAccountDetails() {
        System.out.println("=== Card Account Details ===");
        System.out.println("IBAN: " + iban);
        System.out.println("Account Holder: " + fullname);
        System.out.printf("Balance: %.2f MDL%n", getBalance());
        if (carddeliveryaddress != null && !carddeliveryaddress.isEmpty()) {
            System.out.println("Card delivery address: " + carddeliveryaddress);
        } else {
            System.out.println("No physical card issued.");
        }
    }

    @Override
    public void transferBetweenOwnAccounts(double amount, Accounts destinationAccount) throws InvalidAmountException {
        if (amount <= 0 || amount > getBalance()) {
            System.out.println("Transfer failed: invalid amount or insufficient funds.");
            return;
        }

        setBalance(getBalance() - amount);
        destinationAccount.deposit(amount);

        System.out.printf("Transferred %.2f MDL from Card Account to target account.%n", amount);
    }

}
