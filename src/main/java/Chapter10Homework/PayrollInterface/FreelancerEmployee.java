package Chapter10Homework.PayrollInterface;

public class FreelancerEmployee implements Employee {
    private String name;
    private int nrOfCompletedProjects;
    private int projectPayment = 380;

    public FreelancerEmployee(String name, int nrOfCompletedProjects) {
        this.name = name;
        this.nrOfCompletedProjects = nrOfCompletedProjects;
    }

    @Override
    public double calculateSalary() {
        return projectPayment * nrOfCompletedProjects;
    }

    @Override
    public String getName() {
        return name;
    }
}