package IndividualWork;

import java.util.Scanner;

import static IndividualWork.Validator.*;

import java.sql.Connection;


public class Onboarding {
    private static final Scanner scanner = new Scanner(System.in);
    private final Connection connection;

    public Onboarding(Connection connection) {
        this.connection = connection;
    }

    public User onboardNewUser() {

        System.out.println("Welcome to your lifetime bank!");

        String fullname = onboardfullname();
        String idno = onboardidno();

        String email = onboardEmail();
        String phone = onboardPhone();
        String username = onboardUsername();
        String password = onboardPassword();

        String clientid = IdGenerator.generateClientid();
        // Create user
        User user = new User(username, fullname, idno, email, phone, password, clientid);

        boolean registered = UserRepository.registerUser(user);

        if (registered) {
            System.out.println("User registered successfully. Your Client ID is: " + clientid);
            return user;
        } else {
            System.out.println("User registration failed. Please try again.");
            return null;
        }

    }


    private String onboardfullname() {

        System.out.print("For registration, please enter full name: ");
        return scanner.nextLine();
    }


    private String onboardidno() {
        while (true) {
            System.out.print("Enter personal ID number (IDNO) with 13 digits: ");
            String idno = scanner.nextLine();
            if (!isValidIdno(idno)) {
                System.out.println("Error: The IDNO should contain exactly 13 digits.");
                continue;
            }
            if (isIdnoTaken(idno)) {
                System.out.println("This IDNO is already registered. Please try another.");
                continue;
            }
            System.out.println("IDNO accepted: " + idno);
            return idno;

        }
    }

    private String onboardEmail() {
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

    private String onboardPhone() {
        while (true) {
            System.out.print("Enter local phone number starting with 0: ");
            String phone = scanner.nextLine();

            if (!isValidPhone(phone)) {

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

    private String onboardUsername() {
        while (true) {
            System.out.print("Please choose a username: ");
            String username = scanner.nextLine();
            if (!UserRepository.usernameExists(username)) {
                return username;
            }
            System.out.println("Username already exists. Please choose another.");
        }
    }

    private String onboardPassword() {
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

    public User loginUser() {

        System.out.print("For login, please enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        return UserRepository.login(username, password);

    }
}
