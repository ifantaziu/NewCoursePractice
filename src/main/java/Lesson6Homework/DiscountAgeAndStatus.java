package Lesson6Homework;

import java.util.Scanner;

public class DiscountAgeAndStatus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert the age = ");
        int age = scanner.nextInt();
        System.out.print("Is the person married? (true/false): ");
        boolean isMarried = scanner.nextBoolean();
        String result = (age < 18) ?
                (isMarried ? "Is eligible for discount." : "Is not eligible for discount.") :
                (isMarried ? "Is eligible for discount." : "Is not eligible for discount.");
        System.out.println(result);
    }
}
