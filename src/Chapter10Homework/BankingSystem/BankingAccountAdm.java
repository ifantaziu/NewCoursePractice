package Chapter10Homework.BankingSystem;

public class BankingAccountAdm {
        protected double accountBalance;
        private final String iban;
        private final String accountHolderName;
        public BankingAccountAdm(String iban, String accountHolderName, double accountBalance) {
            this.iban = iban;
            this.accountHolderName = accountHolderName;
            this.accountBalance = accountBalance;
        }
        public String getIban() {
            return iban;
        }
        public String getAccountHolderName() {
            return accountHolderName;
        }
        public double getAccountBalance() {
            return accountBalance;
        }
        public void displayAccountDetails() {
            System.out.println("Account with IBAN: " + getIban());
            System.out.println("Account holder name is: " + getAccountHolderName());
            System.out.println("Current account balance: " + getAccountBalance() + " RON");
        }
        public void cashDeposit(double depositAmount) {
            if (depositAmount > 0) {
                accountBalance += depositAmount;
                System.out.println("Deposit successful! The balance on your account #" + getIban() + " is: " + accountBalance + " RON");
            } else {
                System.out.println("Deposit amount must be greater than zero.");
            }
        }
        public void cashWithdrawal(double withdrawalAmount) {
        }
    }


