package Chapter10Homework.BankingSystem;


class CurrentAccount extends BankingAccountAdm {
    private static final double MIN_BALANCE_AMOUNT = 50.0;

    public CurrentAccount(String iban, String accountHolderName, double initialAccountBalance) {
        super(iban, accountHolderName, initialAccountBalance);
    }

    @Override
    public void cashWithdrawal(double withdrawalAmount) {
        if (withdrawalAmount <= getInitialAccountBalance() && (getInitialAccountBalance() - withdrawalAmount) > MIN_BALANCE_AMOUNT) {
            finalAccountBalance = initialAccountBalance-withdrawalAmount;
            System.out.println("Withdrawal successful! The balance on your account #" + getIban() + " is: " + getFinalAccountBalance() + " RON");
        } else {
            System.out.println("Not enough funds. The balance on your account #" + getIban() + " is: " + getInitialAccountBalance() +
                    " RON and you must keep a minimum of " + MIN_BALANCE_AMOUNT + " RON.");
        }
    }
}