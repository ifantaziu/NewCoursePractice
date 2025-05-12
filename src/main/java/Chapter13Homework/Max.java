package Chapter13Homework;


public class Max {
    public static void main(String[] args) {
        Double temp1 = 36.6;
        Double temp2 = 39.8;

        double higherTemp = Double.max(temp1, temp2);
        System.out.println("The higher temperature is: " + higherTemp);
    }
}

