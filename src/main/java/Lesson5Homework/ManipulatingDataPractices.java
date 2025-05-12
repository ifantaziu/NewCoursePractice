package Lesson5Homework;

import java.util.Scanner;

public class ManipulatingDataPractices {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an integer number = ");
        long number = scanner.nextLong();

        if (number != 0) {
            if (number % 2 == 0)
                System.out.println(number + " it is an even number");
            else
                System.out.println(number + " it is an odd number");


        } else {
            System.out.println("You entered zero, which is neither even nor odd.");
        }

    }
}

