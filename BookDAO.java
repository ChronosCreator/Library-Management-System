import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {

    public void addBook(int id,
            String title,
            String author,
            String status) {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "INSERT INTO books VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setString(4, status);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book addded Successfully!");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "SELECT * FROM books";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " "
                                + rs.getString("title") + " "
                                + rs.getString("author") + " "
                                + rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBook(String title) {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "SELECT * FROM books WHERE title = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;
                System.out.println(
                        rs.getInt("id") + " "
                                + rs.getString("title") + " "
                                + rs.getString("author") + " "
                                + rs.getString("status"));
            }
            if (!found) {
                System.out.println("Book Not Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void issueBook(int id) {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "SELECT status FROM books WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String status = rs.getString("status");
                if ("Issued".equals(status)) {
                    System.out.println("Book already issued! ");

                } else {
                    String updateQuery = "UPDATE books SET status ='Issued' WHERE id =?";
                    PreparedStatement updatePs = con.prepareStatement(updateQuery);
                    updatePs.setInt(1, id);
                    updatePs.executeUpdate();
                    System.out.println("Book issued successfully!");
                }
            } else {
                System.out.println("Book Not Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int id) {
        try {
            Connection con = DatabaseConnection.getConnection();

            String query = "SELECT books FROM books WHERE id =?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String status = rs.getString("status");
                if ("Available".equals(status)) {

                    System.out.println("Book is already available!");

                } else {
                    String updateQuery = "UPDATE books SET status = 'Available' WHERE id =?";
                    PreparedStatement updatePs = con.prepareStatement(updateQuery);
                    updatePs.setInt(1, id);
                    updatePs.executeUpdate();
                    {
                        System.out.println("Book returned successfully!");
                    }

                }
            } else {
                System.out.println("Book not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book Not Found!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
