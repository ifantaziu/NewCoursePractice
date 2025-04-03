package Chapter10Homework.BankingSystem;


class CurrentAccount extends BankingAccountAdm {
    private static final double MIN_BALANCE_AMOUNT = 50.0;

    public CurrentAccount(String iban, String accountHolderName, double accountBalance) {
        super(iban, accountHolderName, accountBalance);
    }

    @Override
    public void cashWithdrawal(double withdrawalAmount) {
        if (withdrawalAmount <= getAccountBalance() && (getAccountBalance() - withdrawalAmount) > MIN_BALANCE_AMOUNT) {
            accountBalance -= withdrawalAmount;
            System.out.println("Withdrawal successful! The balance on your account #" + getIban() + " is: " + getAccountBalance() + " RON");
        } else {
            System.out.println("Not enough funds. The balance on your account #" + getIban() + " is: " + getAccountBalance() +
                    " RON and you must keep a minimum of " + MIN_BALANCE_AMOUNT + " RON.");
        }
    }
}