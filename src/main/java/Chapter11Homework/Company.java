package Chapter11Homework;

public class Company {

    private String name;

    public Company(String name) {
        this.name = name;
    }

    public void printDepartmentInfo(Department dept) {
        System.out.println("Company: " + name + ", Department: " + dept.getName());
    }

    public void printEmployeeInfo(Employee emp) {
        System.out.println("Company: " + name + ", Employee: " + emp.getName());
    }

    public static class Department {
        private String name;

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public class Employee {
        private String name;

        public Employee(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean validateName() {
            HRTool hrTool = new HRTool();
            return hrTool.nameValidation(name);
        }

        private class HRTool {
            public boolean nameValidation(String name) {
                int nameLength = name.trim().length();
                return nameLength >= 3;
            }
        }
    }
}
