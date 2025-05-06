package IndividualWork;

public class ClientIdGenerator {
    private static int counter = 0;

    public static String generateClientId() {
        counter++;
        return String.format("CL%04d", counter);
    }
}