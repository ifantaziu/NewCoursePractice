package Lesson6Homework;

import java.util.Scanner;

public class TemperatureCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert the temperature = ");
        double temperature = scanner.nextDouble();
        double maxTemp = 42.0;
        double minTemp = 37.5;

        if (temperature > maxTemp) {
            System.out.println("Porridge is too hot.");
        } else if (temperature >= minTemp)
            System.out.println("Porridge is just right.");
        else
            System.out.println("Porridge is too cold.");
    }

}

