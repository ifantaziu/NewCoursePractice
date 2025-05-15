package IndividualWork;

public class IbanGenerator {
            private static int counter = 0;

        public static String generateIBAN() {
            counter++;
            return String.format("MDA16FB2252%010d", counter);
        }

}
