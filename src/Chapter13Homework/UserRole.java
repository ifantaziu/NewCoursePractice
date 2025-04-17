package Chapter13Homework;

public enum UserRole {

    ADMIN(100, "You have access to read, copy and modify"),
    MODERATOR(200, "You have access to read and copy"),
    USER(300, "You have read-only access");

    private final int accessLevel;
    private final String accessMessage;

    UserRole(int accessLevel, String accessMessage) {
        this.accessLevel = accessLevel;
        this.accessMessage = accessMessage;
    }

    public int getAccessLevel() {

        return accessLevel;
    }

    public String getAccessMessage() {

        return accessMessage;
    }

    public boolean hasAccessToAdminPanel() {

        return this == ADMIN || this == MODERATOR;
    }
}