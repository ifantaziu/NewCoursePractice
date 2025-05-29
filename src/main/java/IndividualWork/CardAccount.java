package IndividualWork;

public class CardAccount extends AbstractAccount implements Accounts {
    private static int cardCounter = 3000;
    private final String iban;
    private final String accountHolderName;
    private String cardDeliveryAddress = null;

    public CardAccount(String iban, String accountHolderName, double balance, String cardDeliveryAddress) {
        super(balance);
        this.iban = generateIban();
        this.accountHolderName = accountHolderName;
        this.cardDeliveryAddress = cardDeliveryAddress;
    }

    @Override
    public String generateIban() {
        cardCounter++;
        return String.format("CARD2259-%06d", cardCounter);
    }

    @Override
    public String getIban() {
        return iban;
    }

    public String getCardDeliveryAddress() {
        return cardDeliveryAddress;
    }

    public void makePayment(double amount, String merchant) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Invalid payment amount.");
            }
            if (amount > getBalance()) {
                throw new InsufficientFundsException("Insufficient funds for payment.");
            }
            double balance = getBalance()- amount;
            System.out.printf("Paid successful %.2f MDL to %s. Remaining balance: %.2f MDL%n", amount, merchant, balance);
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Override
    public void displayAccountDetails() {
        System.out.println("=== Card Account Details ===");
        System.out.println("IBAN: " + iban);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.printf("Balance: %.2f MDL%n", getBalance());
        if (cardDeliveryAddress != null && !cardDeliveryAddress.isEmpty()) {
            System.out.println("Card delivery address: " + cardDeliveryAddress);
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

        double balance = getBalance()- amount;
        destinationAccount.deposit(amount);

        System.out.printf("Transferred %.2f MDL from Card Account to target account.%n", amount);
    }
}
