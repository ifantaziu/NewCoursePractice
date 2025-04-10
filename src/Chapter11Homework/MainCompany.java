package Chapter11Homework;

public class MainCompany {
    public static void main(String[] args) {
        Company company = new Company("maib");
        Company.Department dept = new Company.Department("Finance");
        Company.Employee employee = company.new Employee("Maria");
        company.printDepartmentInfo(dept);
        if (!employee.validateName()) {
            company.printEmpployeeInfo(employee);
        }
    }
}
