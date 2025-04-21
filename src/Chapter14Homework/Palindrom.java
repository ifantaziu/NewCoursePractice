package Chapter14Homework;

import java.util.Scanner;

public class Palindrom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write down the word to verify if it is palindrome or not:");
        String word = scanner.nextLine();
        isPalindrom(word);
        scanner.close();
    }

    public static void isPalindrom(String word) {
        String reversedWord = new StringBuilder(word).reverse().toString();
        if (word.equalsIgnoreCase(reversedWord)) {
            System.out.println("The word \"" + word + "\" is a palindrome.");
        } else {
            System.out.println("The word \"" + word + "\" is not a palindrome.");
        }
    }
}
