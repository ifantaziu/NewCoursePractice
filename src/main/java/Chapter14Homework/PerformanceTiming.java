package Chapter14Homework;

public class PerformanceTiming {
    public static void main(String[] args) {
        int[] numbers = new int[10000];
        StringBuilder numbersBuilder = new StringBuilder(10000);
        String numbersString = "";
        long startString = System.currentTimeMillis();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
            numbersString = numbersString + numbers[i] + " ";
        }
        long endString = System.currentTimeMillis();
        long runningTimeString = endString - startString;
        System.out.println("Execution time using String: " + runningTimeString + " ms");
       // am utilizat pentru a verifica daca se afiseaza corect:
        // System.out.println("First 100 characters from String: " + numbersString.substring(0, 100));
        long startStringBuilder = System.currentTimeMillis();
        for (int i = 1; i <= 10000; i++) {
            numbersBuilder.append(i).append(" ");
        }
        long endStringBuilder = System.currentTimeMillis();
        long runningTimeStringBuilder = endStringBuilder - startStringBuilder;
        System.out.println("Execution time using StringBuilder: " + runningTimeStringBuilder + " ms");
        // am utilizat pentru a verifica daca se afiseaza corect:
        // System.out.println("First 100 characters from StringBuilder: " + numbersBuilder.substring(0, 100));
    }
}