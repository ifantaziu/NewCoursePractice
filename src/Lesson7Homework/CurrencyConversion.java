package Lesson7Homework;

import java.util.Scanner;

public class CurrencyConversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter currency code (EUR, GBP, INR, AUD): ");
        String currency = scanner.nextLine();
        System.out.print("Enter amount in USD: ");
        double usd = scanner.nextDouble();
        double convertedAmount;
        switch (currency) {
            case "EUR":
                convertedAmount = usd * 0.85;
                System.out.printf("Converted amount in EUR:", convertedAmount);
                break;
            case "GBP":
                convertedAmount = usd * 0.75;
                System.out.printf("Converted amount in GBP:" + convertedAmount);
                break;
            case "INR":
                convertedAmount = usd * 75.0;
                System.out.printf("Converted amount in INR:" + convertedAmount);
                break;
            case "AUD":
                convertedAmount = usd * 1.35;
                System.out.printf("Converted amount in AUD:" + convertedAmount);
                break;
            default:
                System.out.println("Unsupported currency.");
        }
    }
}
