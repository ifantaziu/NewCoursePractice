package Chapter10Homework.PayrollInterface;

public class RemunerationCalculations {

    public static void main(String[] args) {
        Employee[] employees = new Employee[6];
        employees[0] = new FullTimeEmployee("Michael", 3200);
        employees[1] = new FullTimeEmployee("Angela", 4170);
        employees[2] = new PartTimeEmployee("Noah", 39);
        employees[3] = new PartTimeEmployee("Vincent", 44);
        employees[4] = new FreelancerEmployee("Oliver", 6);
        employees[5] = new FreelancerEmployee("Jack", 11);

        Employee newEmp = new PartTimeEmployee("Emily", 30);
        employees = addNewEmployee(employees, newEmp);

        System.out.println("The list of employees and their salaries:");
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i].getName() + " - " + employees[i].calculateSalary() + " EUR");
        }
    }

    public static Employee[] addNewEmployee(Employee[] existingEmployees, Employee newEmployee) {
        Employee[] updatedEmployees = new Employee[existingEmployees.length + 1];
        for (int i = 0; i < existingEmployees.length; i++) {
            updatedEmployees[i] = existingEmployees[i];
        }
        updatedEmployees[existingEmployees.length] = newEmployee;
        return updatedEmployees;
    }
}