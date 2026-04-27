import java.io.*;

public class AdminService {

    // ==============================
    // CREATE - Add new admin
    // ==============================
    public void addAdmin(Admin admin) {
        try {
            FileWriter fw = new FileWriter("admins.txt", true);
            fw.write(admin.toFileFormat() + "\n");
            fw.close();
            System.out.println("✅ Admin added successfully");
        } catch (IOException e) {
            System.out.println("❌ Error adding admin");
        }
    }

    // ==============================
    // READ - View all admins
    // ==============================
    public void viewAdmins() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("admins.txt"));
            String line;

            System.out.println("----- ADMIN LIST -----");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println(
                        "Username: " + data[0] +
                                " | Email: " + data[1] +
                                " | Role: " + data[2]
                );
            }
            br.close();

        } catch (IOException e) {
            System.out.println("❌ Error reading admin file");
        }
    }

    // ==============================
    // UPDATE - Update admin role
    // ==============================
    public void updateAdminRole(String username, String newRole) {
        try {
            File inputFile = new File("admins.txt");
            File tempFile = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            FileWriter fw = new FileWriter(tempFile);

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data[0].equals(username)) {
                    fw.write(data[0] + "," + data[1] + "," + newRole + "\n");
                } else {
                    fw.write(line + "\n");
                }
            }

            br.close();
            fw.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println("✅ Admin updated successfully");

        } catch (IOException e) {
            System.out.println("❌ Error updating admin");
        }
    }

    // ==============================
    // DELETE - Remove admin
    // ==============================
    public void deleteAdmin(String username) {
        try {
            File inputFile = new File("admins.txt");
            File tempFile = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            FileWriter fw = new FileWriter(tempFile);

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (!data[0].equals(username)) {
                    fw.write(line + "\n");
                }
            }

            br.close();
            fw.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println("✅ Admin deleted successfully");

        } catch (IOException e) {
            System.out.println("❌ Error deleting admin");
        }
    }
}
