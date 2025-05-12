package Chapter10Homework.Payroll;

public abstract class Employee {
    private final String name;
    private final int basicSalary;

    public Employee(String name, int basicSalary) {
        this.name = name;
        this.basicSalary = basicSalary;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateSalary();

    public void displayEmployeeInfo() {
        System.out.println("Data about Employee: " + name);
        System.out.println("Initial basic salary: " + basicSalary);
        System.out.println("Final total salary: " + calculateSalary());

    }
}
