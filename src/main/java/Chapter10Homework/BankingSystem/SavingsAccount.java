package Chapter10Homework.BankingSystem;

import java.time.LocalDate;

class SavingsAccount extends BankingAccountAdm {
    private static final double ANNUAL_INTEREST_RATE = 0.03;
    private int withdrawalCount=0;
    private LocalDate lastWithdrawalDate=null;
    private LocalDate lastInterestAppliedDate=null;

    public SavingsAccount(String iban, String accountHolderName, double initialAccountBalance) {
        super(iban, accountHolderName, initialAccountBalance);

    }

    public void applyMonthlyInterest() {
        LocalDate today = LocalDate.now();
        if (lastInterestAppliedDate != null &&
                lastInterestAppliedDate.getYear() == today.getYear() &&
                lastInterestAppliedDate.getMonth() == today.getMonth()) {
            System.out.println("The interest was already applied for this month.");
            return;
        }
        double monthlyInterestRate = ANNUAL_INTEREST_RATE / 12;
        double interest = getInitialAccountBalance() * monthlyInterestRate;
        finalAccountBalance =initialAccountBalance+ interest;
        lastInterestAppliedDate = today;
        System.out.println("For " + today.getMonth() + ", you earned interest: " +
                interest + " RON | New balance on your account #"+getIban()+": " + getFinalAccountBalance() + " RON");
    }

    @Override
    public void cashWithdrawal(double withdrawalAmount) {
        LocalDate today = LocalDate.now();
        if (lastWithdrawalDate != null &&
                lastWithdrawalDate.getYear() == today.getYear() &&
                lastWithdrawalDate.getMonth() == today.getMonth()) {
            System.out.println("Withdrawal failed! You have exceeded your limit for this month.");
            return;
        }
        if (withdrawalAmount > 0 && withdrawalAmount <= getInitialAccountBalance()) {
           finalAccountBalance= initialAccountBalance - withdrawalAmount;
            withdrawalCount++;
            lastWithdrawalDate = today;
            System.out.println("Withdrawal successful! The balance on your account #" + getIban() + " is: " + getFinalAccountBalance() + " RON");
        } else {
            System.out.println("Not enough funds. The balance on your account #" + getIban() + " is: " + getInitialAccountBalance() + " RON");
        }
    }
}
