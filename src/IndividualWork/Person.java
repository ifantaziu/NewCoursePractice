package IndividualWork;

public class Person {
    private final String fullName;
    private final String idNo;

    public Person(String fullName, String idNo) {
        this.fullName = fullName;
        this.idNo = idNo;
    }

    public String getFullName() {
        return fullName;
    }

    public String getIdNo() {
        return idNo;
    }

    @Override
    public String toString() {
        return "Name: " + fullName + ", ID No: " + idNo;
    }
}
