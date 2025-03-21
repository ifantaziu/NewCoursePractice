package Lesson8Homework;

public class MaxIndex {

    public static void main(String[] args) {
        int[] anArray = {65, 245, 2334, 10000, 33, 19, 9876, 54, 10000, 75};
        //  for (int i = 0; i < anArray.length; i++) {
        //      anArray[i] = (i + 3) * 5;
        //  }
        for (int value : anArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        int max = Integer.MIN_VALUE;
        for (int value : anArray) {
            if (value > max) {
                max = value;
            }
        }

        int indexMax = 0;
        for (int i = 0; i < anArray.length; i++) {
            if (anArray[i] == max) {
                indexMax = i;
                break;
            }
        }
        System.out.println("Max Number = " + max);
        System.out.println("Index of Max Number = " + indexMax);
    }
}

