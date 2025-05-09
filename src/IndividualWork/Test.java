package IndividualWork;

public class Test {
    public static void main(String[] args) {

        User user1 = Onboarding.onboardNewUser();
        User user2 = Onboarding.onboardNewUser();

        System.out.println("----------------------------" +
                "\n--- All Registered Users ---");
        UserRegistry.listAllUsers();
    }
}

