package IndividualWork;

public class Validator {
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&*])[A-Za-z0-9!@#\\$%\\^&*]{8,}$";
        return password.matches(regex);
    }

<<<<<<< HEAD
    public static boolean isValidIdNo(String idNo) {
        return idNo.matches("^[0-9]{13}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^0[0-9]{8}$");
=======
    public static boolean isValidIdno(String idno) {
        return idno.matches("^[0-9]{13}$");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("^0[0-9]{8}$");
>>>>>>> a491eab (Save local changes before rebase)
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

<<<<<<< HEAD
    public static boolean isIdNoTaken(String idNo) {
        return UserRegistry.getAllUsers().stream().anyMatch(u -> u.getIdNo().equals(idNo));
=======
    public static boolean isIdnoTaken(String idno) {
        return UserRegistry.getAllUsers().stream().anyMatch(u -> u.getIdno().equals(idno));
>>>>>>> a491eab (Save local changes before rebase)
    }

    public static boolean isEmailTaken(String email) {
        return UserRegistry.getAllUsers().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public static boolean isPhoneTaken(String phone) {
<<<<<<< HEAD
        return UserRegistry.getAllUsers().stream().anyMatch(u -> u.getPhoneNumber().equals(phone));
=======
        return UserRegistry.getAllUsers().stream().anyMatch(u -> u.getPhone().equals(phone));
>>>>>>> a491eab (Save local changes before rebase)
    }
}
