package Chapter15Homework;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
public class AppointmentReminder {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");
        LocalDateTime appointmentDateTime = LocalDateTime.parse("10-05-2025T14:30", formatter);
        LocalDate appointmentDate = appointmentDateTime.toLocalDate();
        LocalDate today = LocalDate.now();
        if (appointmentDate.isAfter(today)) {
            Period remaining = Period.between(today, appointmentDate);
            System.out.println("You have: " + remaining.getDays() + " days before the appointment.");
        } else {
            System.out.println("The appointment already expired.");
        }
    }
}