package Lesson4Homework;

public class MinutesConverter {
    public static void main(String[] args) {
        long minutes = 285816352L;
        long days = minutes / 60 / 24; //intentionat am pastrat in long, pentru ca sa avem zile complete
        long years = days / 365; //intentionat am pastrat in long, pentru ca sa avem anii completi
        System.out.print("In the " + minutes + " minutes, we have " + days + " days and " + years + " years");
    }
}
