package Lesson5Homework;

import java.util.Scanner;
import java.lang.Math;

public class DataCompare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a floating-point number = ");
        float number1 = scanner.nextFloat();
        if (number1 == 0) {
            System.out.println("You entered zero, which is neither positive nor negative.");
        } else if (number1 > 0) {
            System.out.println(number1 + " is a positive number");
        } else {
            System.out.println(number1 + " is a negative number");
        }
        float absoluteValue = Math.abs(number1);
        if (absoluteValue < 1)
            System.out.println(absoluteValue + " is a small number");
        else if (absoluteValue > 1000000)
            System.out.println(absoluteValue + " is a large number");
    }

}

