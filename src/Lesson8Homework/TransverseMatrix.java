package Lesson8Homework;

public class TransverseMatrix {

    public static void main(String[] args) {
        int[][] originalArray = new int[4][7];
        for (int i = 0; i < originalArray.length; i++) {
            for (int j = 0; j < originalArray[i].length; j++) {
                originalArray[i][j] = (i + 3) * (j + 2);
            }
        }
        System.out.println("Original version of matrix:");
        for (int i = 0; i < originalArray.length; i++) {
            for (int j = 0; j < originalArray[i].length; j++) {
                System.out.print(originalArray[i][j] + "\t");
            }
            System.out.println();
        }
        int[][] arrayReversed = new int[originalArray[0].length][originalArray.length];
        for (int i = 0; i < arrayReversed.length; i++) {
            for (int j = 0; j < arrayReversed[i].length; j++) {
                arrayReversed[i][j] = originalArray[j][i];
            }
        }
        System.out.println();
        System.out.println("Reversed version of matrix:");
        for (int i = 0; i < arrayReversed.length; i++) {
            for (int j = 0; j < arrayReversed[i].length; j++) {
                System.out.print(arrayReversed[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

