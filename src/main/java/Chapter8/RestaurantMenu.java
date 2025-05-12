package Chapter8;

import java.util.Scanner;

public class RestaurantMenu {

    public static void main(String[] args) {

        String[] menu = {"Pancake", "Pasta", "Soup", "Pizza", "Burger", "Tiramisu", "Soda", "Cappuccino"};
        double[] prices = {75, 125, 90, 140, 85, 130, 20, 35};
        displayMenu(menu, prices);
        takeOrder(menu, prices);
    }

    public static void displayMenu(String[] menu, double[] prices) {
        System.out.println("Our menu: ");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + "\t" + menu[i] + "\t\t" + prices[i] + " MDL");
        }
    }


    public static void takeOrder(String[] menu, double[] prices) {
        Scanner scanner = new Scanner(System.in);
        double totalPrice = 0.0;

        while (true) {
            System.out.print("Please place your order: ");
            System.out.print("Enter the item number (1 to 8) or 0 to finish: ");
            int selectedItem = scanner.nextInt();

            if (selectedItem == 0) {
                break;
            }

            if (selectedItem < 1 || selectedItem > menu.length) {
                System.out.println("Invalid selection! Please choose a valid item number from the menu.");
                continue;
            }

            System.out.print("Enter the quantity for " + menu[selectedItem - 1] + ": ");
            int quantity = scanner.nextInt();

            if (quantity < 1) {
                System.out.println("Quantity must be at least 1. Please try again.");
                continue;
            }

            double itemTotal = prices[selectedItem - 1] * quantity;
            totalPrice += itemTotal;

            System.out.println("You ordered " + quantity + " " + menu[selectedItem - 1] + "(s) for a total of " + itemTotal + " MDL.");
        }

        System.out.println("\n------ Final Bill ------");
        System.out.println("Total amount: " + totalPrice + " MDL");

    }
}