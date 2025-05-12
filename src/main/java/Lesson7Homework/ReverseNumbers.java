package Lesson7Homework;

import java.util.Scanner;

public class ReverseNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert the number = ");
        int number = scanner.nextInt();
        int absoluteValue = Math.abs(number);
        int reverseNumber = 0;
        while (absoluteValue > 0) {
            int lastDigit = absoluteValue % 10;
            absoluteValue = absoluteValue / 10;
            reverseNumber = reverseNumber * 10 + lastDigit;
        }
        System.out.println("Reversed number: " + reverseNumber);
    }
}