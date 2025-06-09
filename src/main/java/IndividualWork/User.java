package IndividualWork;

public class User {

    private static String fullname;
    private final String idno;
    private final String clientid;
    private final String email;
    private final String phone;
    private final String username;
    private final String password;

    public User(String username, String fullname, String idno, String email, String phone, String password, String clientid) {
        this.username = username;
        this.fullname = fullname;
        this.idno = idno;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.clientid = IdGenerator.generateClientid();
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
