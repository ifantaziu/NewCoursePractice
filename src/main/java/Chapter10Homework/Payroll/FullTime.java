package Chapter10Homework.Payroll;

import java.util.Scanner;

public class FullTime extends Employee {
    private final double annualBonus = 0.75;
    private final int totalPaidDaysOff = 28;
    private int usedPaidDaysOff = 0;
    private boolean tookDaysOff;


    public FullTime(String name, int basicSalary) {
        super(name, basicSalary);
    }

    public void registerDaysOff() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Did the employee " + getName() + " took any paid days off this month? (true/false): ");
        tookDaysOff = scanner.nextBoolean();
        if (tookDaysOff) {
            System.out.print("How many days?: ");
            int days = scanner.nextInt();
            if (days > totalPaidDaysOff) {
                System.out.println("Too many days! Max allowed: " + totalPaidDaysOff);
                usedPaidDaysOff = totalPaidDaysOff;
            } else {
                usedPaidDaysOff = days;
            }
        } else {
            usedPaidDaysOff = 0;
        }
    }

    @Override
    public double calculateSalary() {
        return getBasicSalary() + getBasicSalary() * annualBonus;
    }

    @Override
    public void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("Took paid days off this month: " + tookDaysOff);
        System.out.println("Used paid days off: " + usedPaidDaysOff + "/" + totalPaidDaysOff);
        System.out.println();
    }

}
