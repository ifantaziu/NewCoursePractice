package Lesson8Homework;

public class TemperatureConversion {

    public static void main(String[] args) {
        int[][] tabel = new int[10][2];
        for (int i = 0; i < tabel.length; i++) {
            tabel[i][0] = (i + 1) * 10;
        }
        for (int i = 0; i < tabel.length; i++) {
            tabel[i][1] = tabel[i][0] * 9 / 5 + 32;
        }
        System.out.println("Grade C\t\tGrade F");
        for (int i = 0; i < tabel.length; i++) {
            System.out.println(tabel[i][0] + "\t\t" + tabel[i][1]);
        }
    }
}
