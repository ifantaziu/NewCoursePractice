package IndividualWork;

public class Validator {
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&*])[A-Za-z0-9!@#\\$%\\^&*]{8,}$";
        return password.matches(regex);
    }

    public static boolean isValidIdno(String idno) {
        return idno.matches("^[0-9]{13}$");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("^0[0-9]{8}$");
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    public static String getInvalidEmailInputMessage(String email) {
        if (!email.contains("@")) return "Error: Email must contain the '@' symbol.";
        if (!email.contains(".")) return "Error: Email domain must contain a '.' (e.g., '.com', '.org').";
        if (email.indexOf('@') != email.lastIndexOf('@')) return "Error: Email cannot contain multiple '@' symbols.";
        if (email.startsWith("@") || email.endsWith("@")) return "Error: Email cannot start or end with '@'.";
        return "Error: Invalid email format.";
    }

    public static boolean isIdnoTaken(String idno) {
        return UserRepository.getAllUsers().stream().anyMatch(u -> u.getIdno().equals(idno));
    }

    public static boolean isEmailTaken(String email) {
        return UserRepository.getAllUsers().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public static boolean isPhoneTaken(String phone) {
        return UserRepository.getAllUsers().stream().anyMatch(u -> u.getPhone().equals(phone));
    }
}
