package IndividualWork;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

    public class User extends Person {
        private final String bankClientId;
        private final String email;
        private final String phoneNumber;
        private final Map<String, String> credentials;

        public User(String fullName, String idNo, String email, String phoneNumber, String username, String password) {
            super(fullName, idNo);
            this.bankClientId = ClientIdGenerator.generateClientId();
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.credentials = Collections.unmodifiableMap(createCredentials(username, password));
        }

        private static Map<String, String> createCredentials(String username, String password) {
            Map<String, String> cred = new HashMap<>();
            cred.put(username, password);
            return cred;
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

        public Map<String, String> getCredentials() {
            return credentials;
        }

        public String getUsername() {
            return credentials.keySet().iterator().next();
        }

        @Override
        public String toString() {
            return "Client name: " + getFullName() +
                    "\nIDNO:" + getIdNo() +
                    "\nBank Client ID: " + bankClientId +
                    "\nUsername: " + getUsername() +
                    "\nEmail: " + email +
                    "\nPhone: " + phoneNumber;
        }

}
