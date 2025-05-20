package IndividualWork;

public class User {
    private final String fullName;
    private final String idNo;
    private final String bankClientId;
    private final String email;
    private final String phoneNumber;
    private final String username;
    private final String password;

    public User(String fullName, String idNo, String email, String phoneNumber, String username, String password) {
        this.fullName = fullName;
        this.idNo = idNo;
        this.bankClientId = ClientIdGenerator.generateClientId();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }
    public String getFullName() {
        return fullName;
    }

    public String getIdNo() {
        return idNo;
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
