package Chapter10Homework;

import java.util.Scanner;

public class BankingAccount {
    private double accountBalance;
    public int id;

    BankingAccount(int id, double accountBalance) {
        this.id = id;
        this.accountBalance = accountBalance;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankingAccount acc01 = new BankingAccount(345678, 5000);
        BankingAccount acc02 = new BankingAccount(345679, 350);
        BankingAccount acc03 = new BankingAccount(345680, 1200);

        System.out.println("Please enter the amount you need to withdraw from your banking account #id: " + acc01.id);
        double withdrawalAmount01 = scanner.nextDouble();
        acc01.cashWithdrawal(withdrawalAmount01);

        System.out.println("Please enter the amount you want to deposit into your banking account #id: " + acc02.id);
        double cashDeposit02 = scanner.nextDouble();
        acc02.cashDeposit(cashDeposit02);

        System.out.println("Please enter the amount you need to withdraw from your banking account #id: " + acc03.id);
        double withdrawalAmount03 = scanner.nextDouble();
        acc03.cashWithdrawal(withdrawalAmount03);

        acc01.displayAccountDetails();
        acc02.displayAccountDetails();
        acc03.displayAccountDetails();

        scanner.close();
    }

    public double cashWithdrawal(double withdrawalAmount) {
        if (withdrawalAmount <= accountBalance) {
            accountBalance -= withdrawalAmount; // Withdraw money
            System.out.println("Withdrawal successful! Your current balance is: " + accountBalance);
        } else {
            System.out.println("Not enough funds on your account. Your balance is: " + accountBalance);
        }
        return accountBalance;
    }

    public double cashDeposit(double depositAmount) {
        if (depositAmount > 0) {
            accountBalance += depositAmount; // Deposit money
            System.out.println("Deposit successful! Your current balance is: " + accountBalance);
        } else {
            System.out.println("Deposit amount must be greater than zero.");
        }
        return accountBalance;
    }

    public void displayAccountDetails() {
        System.out.println("Account with ID: " + id);
        System.out.println("Current account balance: " + getAccountBalance()); // Access account balance via getter
    }

    public double getAccountBalance() {
        return accountBalance;
    }
}