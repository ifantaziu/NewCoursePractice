package Lesson5Homework;
import java.util.Scanner;
public class Discount {
    public static void main (String[] args){
        Scanner scanner = new Scanner (System.in);
        System.out.println ("Insert the amount of total purchase= ");
        float price = scanner.nextFloat();
        int discount = (price >100)?10:0;
        if (discount !=0) {
            double finalPrice = price - ( price*discount/100.0);
            System.out.println ("The amount of total purchase, including discount=  "  + finalPrice);
        }
    }
}
