package Lesson8Homework;

import java.util.Scanner;

public class ArrayFind {
    public static void main(String[] args) {
        int[] anArray = new int[12];
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (i + 3) * 5;
        }
        System.out.print("Array elements: ");
        for (int value : anArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the value to search for: ");
        int searchValue = scanner.nextInt();
        boolean found = false;
        for (int value : anArray) {
            if (value == searchValue) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("The value " + searchValue + " is present in the array.");
        } else {
            System.out.println("The current array does not contain the value you are searching for.");
        }
        scanner.close();
    }
}
