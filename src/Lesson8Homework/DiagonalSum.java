package Lesson8Homework;

public class DiagonalSum {
    public static void main(String[] args) {
        int[][] multiArray = new int[7][7];
        for (int i = 0; i < multiArray.length; i++) {
            for (int j = 0; j < multiArray[i].length; j++) {
                multiArray[i][j] = (i + 3) * (j + 2);
            }
        }
        System.out.println("Matricea generată:");
        for (int i = 0; i < multiArray.length; i++) {
            for (int j = 0; j < multiArray[i].length; j++) {
                System.out.print(multiArray[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        int sumMainDiagonal = 0;
        int sumSecondaryDiagonal = 0;
        for (int i = 0; i < multiArray.length; i++) {
            sumMainDiagonal += multiArray[i][i];
        }

        for (int i = 0; i < multiArray.length; i++) {
            sumSecondaryDiagonal += multiArray[i][multiArray.length - 1 - i];
        }
        System.out.println("Sum of Numbers from main diagonal = " + sumMainDiagonal);
        System.out.println("Sum of Numbers from secondary diagonal= " + sumSecondaryDiagonal);
    }
}
