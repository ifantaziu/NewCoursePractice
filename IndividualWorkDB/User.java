package IndividualWork;

public class User {
    private static String fullname;
    private final String idno;
    private final String clientid;
    private final String email;
    private final String phone;
    private final String username;
    private final String password;

    public User(String fullName, String idno, String clientid, String email, String phone, String username, String password) {
        this.fullname = fullName;
        this.idno = idno;
        this.clientid = IdGenerator.generateClientid();
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;

    }
    public static String getFullname() {
        return fullname;
    }

    public String getIdno() {
        return idno;
    }

    public String getClientid() {
        return clientid;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Client name: " + getFullname() +
                "\nIDNO:" + getIdno() +
                "\nBank Client ID: " + getClientid() +
                "\nUsername: " + getUsername() +
                "\nEmail: " + getEmail() +
                "\nPhone: " + getPhone();
    }

}
