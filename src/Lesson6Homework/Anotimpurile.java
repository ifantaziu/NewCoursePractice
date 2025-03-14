package Lesson6Homework;

import java.util.Scanner;

public class Anotimpurile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți numărul lunii: ");
        int nrLunii = scanner.nextInt();
        switch (nrLunii) {
            case 12:
            case 1:
            case 2:
                System.out.println("Iarna");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("Primăvara");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("Vara");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("Toamna");
                break;
            default:
                System.out.println("Nu este o lună validă!");
        }
    }
}