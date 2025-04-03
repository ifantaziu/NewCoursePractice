package Chapter10Homework.BankingSystem;

public class BankingSystem {
    public static void main(String[] args) {
        double withdrawalAmount = 130.5;
        double depositAmount = 350.0;
        CurrentAccount c01 = new CurrentAccount("MDAC123456", "Irina", 2500.0);
        CurrentAccount c02 = new CurrentAccount("MDAC123502", "Maria", 370.0);
        CurrentAccount c03 = new CurrentAccount("MDAC128973", "Ion", 62.5);
        SavingsAccount s01 = new SavingsAccount("MDAS086533", "Irina", 80.0);
        SavingsAccount s02 = new SavingsAccount("MDAS123502", "Maria", 500.0);
        SavingsAccount s03 = new SavingsAccount("MDAS875359", "Ion", 1200.0);
        c01.displayAccountDetails();
        c02.displayAccountDetails();
        c03.displayAccountDetails();
        s01.displayAccountDetails();
        s02.displayAccountDetails();
        s03.displayAccountDetails();
        System.out.println();
        c01.cashWithdrawal(withdrawalAmount);
        c02.cashDeposit(depositAmount);
        c03.cashWithdrawal(withdrawalAmount);
        s01.cashWithdrawal(withdrawalAmount);
        s02.cashDeposit(depositAmount);
        s03.cashWithdrawal(withdrawalAmount);
        s03.cashWithdrawal(withdrawalAmount);
        System.out.println();
        s01.applyMonthlyInterest();
        s02.applyMonthlyInterest();
        s03.applyMonthlyInterest();
        System.out.println();
        c01.displayAccountDetails();
        c02.displayAccountDetails();
        c03.displayAccountDetails();
        s01.displayAccountDetails();
        s02.displayAccountDetails();
        s03.displayAccountDetails();
    }
}