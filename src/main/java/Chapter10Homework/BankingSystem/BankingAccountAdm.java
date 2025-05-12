package Chapter10Homework.BankingSystem;

public class BankingAccountAdm {
        protected double initialAccountBalance;
    public double finalAccountBalance;
        private final String iban;
        private final String accountHolderName;
        public BankingAccountAdm(String iban, String accountHolderName, double initialAccountBalance) {
            this.iban = iban;
            this.accountHolderName = accountHolderName;
            this.initialAccountBalance = initialAccountBalance;
        }
        public String getIban() {
            return iban;
        }
        public String getAccountHolderName() {
            return accountHolderName;
        }
        public double getInitialAccountBalance() {
            return initialAccountBalance;
        }

    public double getFinalAccountBalance() {
        return finalAccountBalance;
    }

    public void displayAccountDetails() {
        System.out.println("Account details " );
            System.out.println("Account with IBAN: " + getIban());
            System.out.println("Account holder name is: " + getAccountHolderName());
            System.out.println("Initial account balance: " + getInitialAccountBalance() + " RON");
        System.out.println("Current account balance: " + getFinalAccountBalance() + " RON");
        System.out.println();
        }
        public void cashDeposit(double depositAmount) {
            if (depositAmount > 0) {
                finalAccountBalance= initialAccountBalance+ depositAmount;
                System.out.println("Deposit successful! The balance on your account #" + getIban() + " is: " + finalAccountBalance + " RON");
            } else {
                System.out.println("Deposit amount must be greater than zero.");
            }
        }
        public void cashWithdrawal(double withdrawalAmount) {
        }
    }


