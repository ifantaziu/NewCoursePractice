package IndividualWork;

import java.util.Scanner;

import static IndividualWork.UserRegistry.getInvalidEmailInputMessage;
import static IndividualWork.UserRegistry.isValidEmail;

public class Onboarding {
    public static User onboardNewUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Welcome to your lifetime bank! " +
                "\nFor registration, please enter full name: ");
        String fullName = scanner.nextLine();

        String idNo;
        while (true) {
            System.out.print("Enter personal ID number (IDNO) with 13 digits: ");
            idNo = scanner.nextLine();
            if (!idNo.matches("^[0-9]{13}$")) {
                System.out.println("Error: The IDNO should contain digits only.");
                continue;
            }
            if (idNo.length() != 13) {
                System.out.println("Error: The IDNO must have exactly 13 digits.");
            } else {
                System.out.println("IDNO accepted: " + idNo);
                break;
            }
        }

        String email;
        while (true) {
            System.out.print("Enter email (e.g.examplename@mail.com): ");
            email = scanner.nextLine();

            if (isValidEmail(email)) {
                System.out.println("Email accepted: " + email);
                break;
            } else {
                String errorMessage = getInvalidEmailInputMessage(email);
                System.out.println(errorMessage);
            }
        }

        String phone;
        while (true) {
            System.out.print("Enter local phone number starting with 0: ");
            phone = scanner.nextLine();
            if (!phone.matches("^[0-9]+$")) {
                System.out.println("Error: The phone number should contain digits only.");
                continue;
            }
            if (!phone.startsWith("0")) {
                System.out.println("Error: The phone number must start with '0'.");
                continue;
            }
            if (phone.length() != 9) {
                System.out.println("Error: The phone number must have exactly 9 digits.");
            } else {
                System.out.println("Phone number accepted: " + phone);
                break;
            }
        }

        String username;
        do {
            System.out.print("Choose a username: ");
            username = scanner.nextLine();
            if (UserRegistry.usernameExists(username)) {
                System.out.println("Username already exists. Please choose another.");
            }
        } while (UserRegistry.usernameExists(username));

        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        // Create user
        User user = new User(fullName, idNo, email, phone, username, password);

        // Register user
        UserRegistry.registerUser(user);

        System.out.println("\n User created successfully:");
        System.out.println(user);

        return user;
    }
}

