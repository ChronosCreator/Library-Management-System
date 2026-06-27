import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/library_db";
            String username = "root";
            String password = "Sumit@091205";

            Connection con = DriverManager.getConnection(url, username, password);

            return con;
        } catch (Exception e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        return null;
    }

}
