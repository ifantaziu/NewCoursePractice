package Lesson8Homework;

public class ArrayMinMax {
    public static void main(String[] args) {
        int[] anArray = new int[12];
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (i + 3) * 5;
        }
        for (int value : anArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int value : anArray) {
            if (value < min) {
                min = value;
            }
            if (max < value) {
                max = value;
            }
        }
        System.out.println("Min Number = " + min);
        System.out.println("Max Number = " + max);
    }
}