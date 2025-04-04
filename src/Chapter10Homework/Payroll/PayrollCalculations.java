package Chapter10Homework.Payroll;

public class PayrollCalculations {
    public static void main(String[] args) {
        FullTime employee1 = new FullTime("Mark", 1200);
        FullTime employee2 = new FullTime("John", 2000);
        FullTime employee3 = new FullTime("Steve", 1700);
        PartTime employee4 = new PartTime("Iris", 300, 40);
        PartTime employee5 = new PartTime("Van", 280, 42);
        PartTime employee6 = new PartTime("Nick", 350, 36);

        employee1.registerDaysOff();
        employee2.registerDaysOff();
        employee3.registerDaysOff();

        employee4.addDepartments();
        employee5.addDepartments();
        employee6.addDepartments();

        employee1.displayEmployeeInfo();
        employee2.displayEmployeeInfo();
        employee3.displayEmployeeInfo();
        employee4.displayEmployeeInfo();
        employee5.displayEmployeeInfo();
        employee6.displayEmployeeInfo();
    }
}