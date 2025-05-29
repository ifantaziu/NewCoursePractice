package IndividualWork;

import java.util.Scanner;

import static IndividualWork.Validator.*;

public class Onboarding {
    private static final Scanner scanner = new Scanner(System.in);

    public static User onboardNewUser() {
        System.out.print("Welcome to your lifetime bank! ");
        String fullName = onboardFullName();
        String idNo = onboardIdNo();
        String email = onboardEmail();
        String phone = onboardPhone();
        String username = onboardUsername();
        String password = onboardPassword();
        // Create user
        User user = new User(fullName, idNo, email, phone, username, password);

        // Register user
        UserRegistry.registerUser(user);

        System.out.println("\n User created successfully:");
        System.out.println(user);

        return user;
    }

    public static String onboardFullName() {
        System.out.print("For registration, please enter full name: ");
        return scanner.nextLine();
    }

    public static String onboardIdNo() {
        while (true) {
            System.out.print("Enter personal ID number (IDNO) with 13 digits: ");
            String idNo = scanner.nextLine();
            if (!isValidIdNo(idNo)) {
                System.out.println("Error: The IDNO should contain exactly 13 digits.");
                continue;
            }
            if (isIdNoTaken(idNo)) {
                System.out.println("This IDNO is already registered. Please try another.");
                continue;
            }
            System.out.println("IDNO accepted: " + idNo);
            return idNo;
        }
    }

    public static String onboardEmail() {
        while (true) {
            System.out.print("Enter email (e.g. examplename@mail.com): ");
            String email = scanner.nextLine();

            if (!isValidEmail(email)) {
                System.out.println(getInvalidEmailInputMessage(email));
                continue;
            }
            if (isEmailTaken(email)) {
                System.out.println("This email is already registered. Please use another.");
                continue;
            }
            System.out.println("Email accepted: " + email);
            return email;
        }
    }

    public static String onboardPhone() {
        while (true) {
            System.out.print("Enter local phone number starting with 0: ");
            String phone = scanner.nextLine();
            if (!isValidPhoneNumber(phone)) {
                System.out.println("Error: Phone number must start with 0 and have 9 digits.");
                continue;
            }
            if (isPhoneTaken(phone)) {
                System.out.println("This phone number is already registered.");
                continue;
            }
            System.out.println("Phone number accepted: " + phone);
            return phone;
        }
    }

    public static String onboardUsername() {
        while (true) {
            System.out.print("Please choose a username: ");
            String username = scanner.nextLine();
            if (!UserRegistry.usernameExists(username)) {
                return username;
            }
            System.out.println("Username already exists. Please choose another.");
        }
    }

    public static String onboardPassword() {
        while (true) {
            System.out.print("Please choose a password: ");
            String password = scanner.nextLine();
            if (isValidPassword(password)) {
                System.out.println("Password is valid!");
                return password;
            } else {
                System.out.println("The password needs to have:" +
                        "\n- at least 8 characters;" +
                        "\n- one uppercase (A-Z);" +
                        "\n- one digit (0-9);" +
                        "\n- one special character (!@#$%^&*)." +
                        "\nPlease try again.");
            }
        }
    }
}



