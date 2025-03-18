package Lesson8Homework;

import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        int[] anArray;
        anArray = new int[5];
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (i + 3) * 4;
        }
        for (int value : anArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        //Array cu dimensiunea si valorile declarate de user
        Scanner scanner = new Scanner(System.in);
        int[] anArray1;
        anArray1 = new int[scanner.nextInt()];
        for (int i = 0; i < anArray1.length; i++) {
            System.out.print("Please enter the values ");
            anArray1[i] = scanner.nextInt();
        }
        for (int value : anArray1) {
            System.out.print(value + " ");
        }
        scanner.close();
    }
}