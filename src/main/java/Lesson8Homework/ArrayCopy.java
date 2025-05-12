package Lesson8Homework;

public class ArrayCopy {
    public static void main(String[] args) {
        String[] anArray = {"banana ", "orange ", "strawberry ", "pineapple ", "coconut ", "kiwi "};
        System.out.println("Original Array: ");
        for (String value : anArray) {
            System.out.print(value + " ");
        }
        String[] copyArray;
        copyArray = new String[anArray.length];
        for (int j = 0; j < copyArray.length; j++) {
            copyArray[j] = anArray[j];
        }
        System.out.println();
        System.out.println();
        System.out.println("Copy Array: ");
        for (String value1 : copyArray) {
            System.out.print(value1 + " ");
        }
    }
}

