package Chapter15Homework;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class MembershipManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.print("Enter membership activation date (yyyy-MM-dd): ");
        String inputDate = scanner.nextLine();
        LocalDate membershipActivationDate = LocalDate.parse(inputDate, formatter);
        Period activeTerm = Period.ofMonths(3);
        LocalDate membershipExpireDate = membershipActivationDate.plus(activeTerm);
        System.out.println("Membership expire date: " + membershipExpireDate);
        checkMembershipStatus(membershipExpireDate);
        scanner.close();
    }
    public static void checkMembershipStatus(LocalDate membershipExpireDate) {
        LocalDate today = LocalDate.now();
        if (membershipExpireDate.isAfter(today)) {
            Period remaining = Period.between(today, membershipExpireDate);
            System.out.println("The membership is still active! You can use it for: " +
                    remaining.getMonths() + " months and " + remaining.getDays() + " days.");
        } else {
            System.out.println("The membership has expired.");
        }
    }
}