package IndividualWork;

public class User extends Person {
    private final String bankClientId;
    private final String email;
    private final String phoneNumber;
    private final String username;
    private final String password;

    public User(String fullName, String idNo, String email, String phoneNumber, String username, String password) {
        super(fullName, idNo);
        this.bankClientId = ClientIdGenerator.generateClientId();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public String getBankClientId() {
        return bankClientId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Client name: " + getFullName() +
                "\nIDNO:" + getIdNo() +
                "\nBank Client ID: " + getBankClientId() +
                "\nUsername: " + getUsername() +
                "\nEmail: " + getEmail() +
                "\nPhone: " + getPhoneNumber();
    }

}
