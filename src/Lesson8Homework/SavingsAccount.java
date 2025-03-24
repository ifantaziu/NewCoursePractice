package Lesson8Homework;

public class SavingsAccount {
    public static double annualInterestRate = 4.0;
    private double savingsBalance;

    public SavingsAccount(double balance) {
        this.savingsBalance = balance;
    }

    public void calculateMonthlyInterest() {
        double monthlyInterest = (savingsBalance * (annualInterestRate / 100)) / 12;
        savingsBalance += monthlyInterest;
    }

    public static void modifyInterestRate(double newRate) {
        annualInterestRate = newRate;
    }

    public static void main(String[] args) {
        SavingsAccount saver1 = new SavingsAccount(2000.00);
        SavingsAccount saver2 = new SavingsAccount(3000.00);
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();
        System.out.println("The saving balance for saver1 account, after first month, with 4% of Annual Interest Rate = "
                + (Math.round(saver1.savingsBalance * 100.0) / 100.0) + " USD");
        System.out.println("The saving balance for saver2 account, after first month, with 4% of Annual Interest Rate = "
                + (Math.round(saver2.savingsBalance * 100.0) / 100.0) + " USD");

        SavingsAccount.modifyInterestRate(5.0);
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();
        System.out.println("The saving balance for saver1 account, after second month, with 5% of Annual Interest Rate = "
                + (Math.round(saver1.savingsBalance * 100.0) / 100.0) + " USD");
        System.out.println("The saving balance for saver2 account, after second month, with 5% of Annual Interest Rate = "
                + (Math.round(saver2.savingsBalance * 100.0) / 100.0) + " USD");
    }
}

