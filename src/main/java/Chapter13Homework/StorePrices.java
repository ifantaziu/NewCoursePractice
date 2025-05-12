package Chapter13Homework;

import java.util.Scanner;

import static java.lang.Math.round;

public class StorePrices {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the quantity for the purchased products");
        String quantity = scanner.nextLine();
        double doubleQuantity = Double.parseDouble(quantity);
        System.out.println("Please enter the price for the purchased products");
        String price = scanner.nextLine();
        double doublePrice = Double.parseDouble(price);
        double totalCost = round(doubleQuantity * doublePrice);
        String stringTotalCost = Double.toString(totalCost);
        System.out.println("Your total cost is " + stringTotalCost + " MDL");
        scanner.close();
    }
}
