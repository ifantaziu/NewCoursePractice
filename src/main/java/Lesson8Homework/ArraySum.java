package Lesson8Homework;

public class ArraySum {
    public static void main(String[] args) {
        //Array unidimensional

        int[] anArray = new int[12];
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (i + 3) * 5;
        }
        System.out.println("Unidimensional Array:");
        for (int value : anArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.println();
        int sumEven = 0;
        int sumOdd = 0;
        for (int value : anArray) {
            if (value % 2 == 0) {
                sumEven += value;
            } else {
                sumOdd += value;
            }
        }
        System.out.println("Sum of Even Numbers = " + sumEven);
        System.out.println("Sum of Odd Numbers = " + sumOdd);

        //Array bidimensional

        int[][] multiArray = new int[12][7];

        for (int i = 0; i < multiArray.length; i++) {
            for (int j = 0; j < multiArray[i].length; j++) {
                multiArray[i][j] = (i + 3) * (j + 2);
            }
        }
        System.out.println();
        System.out.println("Bidimensional Array:");
        for (int i = 0; i < multiArray.length; i++) {
            for (int j = 0; j < multiArray[i].length; j++) {
                System.out.print(multiArray[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        int sumEven2 = 0;
        int sumOdd2 = 0;
        for (int i = 0; i < multiArray.length; i++) {
            for (int j = 0; j < multiArray[i].length; j++) {
                if (multiArray[i][j] % 2 == 0) {
                    sumEven2 += multiArray[i][j];
                } else {
                    sumOdd2 += multiArray[i][j];
                }
            }
        }
        System.out.println("Sum of Even Numbers = " + sumEven2);
        System.out.println("Sum of Odd Numbers = " + sumOdd2);
    }
}