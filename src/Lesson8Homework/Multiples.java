package Lesson8Homework;

public class Multiples {
    public static void main(String[] args) {
        int number;
        for (number = 100; number % 7 != 0 || number % 9 != 0; number++) ;
        System.out.println("The first number multiple of 7 and 9 greater than 100 is: " + number);
    }
}
