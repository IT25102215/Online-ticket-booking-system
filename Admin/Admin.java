
    public class Admin extends User {

        private String email;
        private String role;

        public Admin(String username, String email, String role) {
            this.username = username;
            this.email = email;
            this.role = role;
        }

        // Getter
        public String getEmail() {
            return email;
        }

        // Setter
        public void setRole(String role) {
            this.role = role;
        }

        public String toFileFormat() {
            return username + "," + email + "," + role;
        }
    }


