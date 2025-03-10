package Lesson4Homework;

public class TaxCalculator {
    public static void main(String[] args) {
        double price = 45.25;
        double tax = 0.12;
        int quantity = 7;
        double total = price * quantity * tax;
        System.out.println("Total taxes = " + total);
        System.out.println("Total cost with taxe is = " + (price * quantity + total));
        System.out.println("Total cost with taxe is = " + (price * quantity * (1 + tax)));


    }

}
