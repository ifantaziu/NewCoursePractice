package Chapter15Homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WeeklyCourse {
    public static void main(String[] args) {
        String input = "Thursday-24-04-2025T17:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE-dd-MM-yyyy'T'HH:mm", Locale.ENGLISH);
        LocalDateTime firstClass = LocalDateTime.parse(input, formatter);
        System.out.println(firstClass);
        int totalWeeks = 5;
        LocalDateTime classDate = firstClass;
        for (int i = 1; i <= totalWeeks; i++) {
            System.out.println("Class " + i + " is on: " + classDate.format(formatter));
            classDate = classDate.plusWeeks(1);
        }
    }
}