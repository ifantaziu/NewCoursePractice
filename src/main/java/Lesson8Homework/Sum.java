package Lesson8Homework;

import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            System.out.println("Enter a positive number:");
            number = scanner.nextInt();
            if (number < 0) {
                System.out.println("The number is negative. Please enter a positive number.");
            } else {
                break;
            }
        }
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        System.out.println("Sum of digits: " + sum);
        scanner.close();
    }
}
