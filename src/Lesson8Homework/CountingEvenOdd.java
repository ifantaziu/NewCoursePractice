package Lesson8Homework;

public class CountingEvenOdd {
    public static void main(String[] args) {
        int[] anArray = new int[23];
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (i + 3) * 3;
        }
        for (int value : anArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        int numEven = 0;
        int numOdd = 0;
        for (int value : anArray) {
            if (value % 2 == 0) {
                numEven++;
            } else {
                numOdd++;
            }
        }
        System.out.println("In the Array are  " + numEven + " Even Numbers  ");
        System.out.println("In the Array are  " + numOdd + " Odd Numbers  ");
    }
}
