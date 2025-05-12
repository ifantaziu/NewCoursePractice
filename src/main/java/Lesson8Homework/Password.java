package Lesson8Homework;

import java.util.Scanner;

public class Password {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String systemPassword = "Primavar@2025";
        String password;
        int attempts = 3;
        while (attempts > 0) {
            System.out.println("Enter your password:");
            password = scanner.nextLine();
            if (password.equals(systemPassword)) {
                System.out.println("Access granted!");
                break;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Incorrect password. You have " + attempts + " attempts left.");
                } else {
                    System.out.println("You have reached the maximum password attempts. User blocked.");
                }
            }
        }
        scanner.close();
    }
}
