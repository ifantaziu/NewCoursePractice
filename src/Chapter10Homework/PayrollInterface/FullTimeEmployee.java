package Chapter10Homework.PayrollInterface;

public class FullTimeEmployee implements Employee {
    private String name;
    private double monthlyWage;

    public FullTimeEmployee(String name, double monthlyWage) {
        this.name = name;
        this.monthlyWage = monthlyWage;
    }

    @Override
    public double calculateSalary() {
        return monthlyWage;
    }

    @Override
    public String getName() {
        return name;
    }
}