public class Main {


        public static void main(String[] args) {

            AdminService service = new AdminService();

            // CREATE
            Admin admin1 = new Admin("admin1", "admin1@gmail.com", "SUPER_ADMIN");
            service.addAdmin(admin1);

            // READ
            service.viewAdmins();

            // UPDATE
            service.updateAdminRole("admin1", "ADMIN");

            // DELETE
            service.deleteAdmin("admin1");
        }

}
