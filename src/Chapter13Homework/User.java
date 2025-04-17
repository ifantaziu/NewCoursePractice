package Chapter13Homework;

public class User {
    private String name;
    private UserRole role;

    public User(String name, UserRole role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {

        return name;
    }

    public UserRole getRole() {

        return role;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("John", UserRole.ADMIN),
                new User("Erika", UserRole.MODERATOR),
                new User("Dino", UserRole.USER),
                new User("Fisher", UserRole.MODERATOR)
        };

        for (User user : users) {
            switch (user.getRole()) {
                case ADMIN:
                    System.out.println(user.getName() + " - Full admin access");
                    break;
                case MODERATOR:
                    System.out.println(user.getName() + " - Limited admin access");
                    break;
                case USER:
                    System.out.println(user.getName() + " - Basic user access");
                    break;
            }

            if (user.getRole().hasAccessToAdminPanel()) {
                System.out.println(user.getName() + " has access to the admin panel.");
            } else {
                System.out.println(user.getName() + " does NOT have access to the admin panel.");
            }

            System.out.println("Permissions: " + user.getRole().getAccessMessage());
            System.out.println();
        }
    }
}