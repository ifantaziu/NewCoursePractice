package Chapter11Homework;

public class Company {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public void printDepartmentInfo(Department dept) {
        System.out.println("Company: " + name + ", Department: " + dept.getName());
    }

    public void printEmpployeeInfo(Employee emp) {
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
            HRTool hrtool = new HRTool();
            return hrtool.nameValidation(name);
        }


        private class HRTool {
            public boolean nameValidation(String name) {
                int nameLength = name.trim().length();
                boolean validation = false;
                if (nameLength < 3) ;
                return validation;
            }
        }
    }
}
