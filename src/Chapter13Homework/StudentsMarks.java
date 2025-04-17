package Chapter13Homework;

public class StudentsMarks {
    Integer mark;
    String name;

    StudentsMarks(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }

    public Integer getMark() {
        return mark;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        StudentsMarks mark1 = new StudentsMarks("John", 9);
        StudentsMarks mark2 = new StudentsMarks("Ben", 8);

        int comparison = Integer.compare(mark1.getMark(), mark2.getMark());

        StudentsMarks higher;
        if (comparison >= 0) {
            higher = mark1;
        } else {
            higher = mark2;
        }

        System.out.println("The higher mark has student: " + higher.getName() + " with mark " + higher.getMark());
    }
}