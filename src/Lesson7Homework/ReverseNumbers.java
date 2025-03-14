package Lesson7Homework;
import java.util.Scanner;
public class ReverseNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert the number = ");
        int number = scanner.nextInt();
        int absoluteValue = Math.abs(number);
        String line = "" + absoluteValue;
        for (int i = line.length() - 1; i >= 0; --i) {
            System.out.print(line.charAt(i));
        }
        System.out.println();
    }
}