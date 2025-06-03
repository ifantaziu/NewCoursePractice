package IndividualWork;

import java.util.UUID;

public class IdGenerator {
    public static String generateClientid() {
        return "CL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public static String generateIban(String accountType) {
        String prefix;
        switch (accountType.toLowerCase()) {
            case "general" -> prefix = "GEN2251";
            case "savings" -> prefix = "SAV2252";
            case "card" -> prefix = "CARD2259";
            default -> throw new IllegalArgumentException("Invalid account type for IBAN.");
        }
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}