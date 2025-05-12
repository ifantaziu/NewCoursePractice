package Chapter10Homework.PayrollInterface;

public class PartTimeEmployee implements Employee {
    private String name;
    private int workedHours;
    private double hourlyWage = 8.25;

    public PartTimeEmployee(String name, int workedHours) {
        this.name = name;
        this.workedHours = workedHours;
    }

    @Override
    public double calculateSalary() {
        return hourlyWage * workedHours;
    }

    @Override
    public String getName() {
        return name;
    }
}