package Chapter10Homework.Payroll;

import java.util.Scanner;

public class PartTime extends Employee {
    private double hourlyWage;
    private int workedHours;
    private String[] departments;

    public PartTime(String name, int basicSalary, int workedHours) {
        super(name, basicSalary);
        this.workedHours = workedHours;
        this.hourlyWage = 10.0;
        this.departments = new String[0];
    }

    public void addDepartments() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of departments worked in this month for " + getName() + ": ");
        int n = scanner.nextInt();
        scanner.nextLine();
        departments = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter department " + (i + 1) + ": ");
            departments[i] = scanner.nextLine();
        }
    }

    @Override
    public double calculateSalary() {
        return workedHours * hourlyWage;
    }

    @Override
    public void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("Worked hours: " + workedHours);
        System.out.println("Hourly wage: " + hourlyWage);
        System.out.print("Departments: ");
        for (String dep : departments) {
            System.out.print(dep + ", ");
        }
        System.out.println();
    }
}
