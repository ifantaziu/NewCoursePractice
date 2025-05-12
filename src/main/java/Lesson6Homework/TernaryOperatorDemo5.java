package Lesson6Homework;

import java.util.Scanner;

public class TernaryOperatorDemo5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert the color code = ");
        int colorCode = scanner.nextInt();
        String color = (colorCode == 100) ? "Yellow" :
                (colorCode == 101) ? "Green" :
                        (colorCode == 102) ? "Red" :
                                "Invalid color";
        System.out.println(color);
        scanner.close();
    }
}