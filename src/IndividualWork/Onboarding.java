package IndividualWork;

import java.util.Scanner;

public class Onboarding {
    public static User onboardNewUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Welkome to your lifetime bank! " +
                "\nFor registration, please enter full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter personal ID number (IDNO): ");
        String idNo = scanner.nextLine();

        System.out.print("Enter email (i.e.examplename@mail.com): ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number starting with 0: ");
        String phone = scanner.nextLine();

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

