package Chapter14Homework;

import java.util.Scanner;

public class StringPractice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write down something about Java:");
        String text = scanner.nextLine();
        String upperText = text.toUpperCase();
        System.out.println("Upper case modified text: " + upperText);
        System.out.println("Modified text by replacing JAVA into PYTHON: " + upperText.replace("JAVA", "PYTHON"));
        System.out.println("Your text starts with “HELLO”: " + upperText.startsWith("HELLO"));
        System.out.println("Your text ends with “!”: " + upperText.endsWith("!"));
        scanner.close();
    }
}
