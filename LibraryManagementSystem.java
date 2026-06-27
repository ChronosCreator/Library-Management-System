import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO dao = new BookDAO();

        while (true) {
            System.out.println("\n===== Library Management System  =====");
            System.out.println("1.Add Book");
            System.out.println("2.View Books");
            System.out.println("3.Search Book");
            System.out.println("4.Issue Book");
            System.out.println("5.Return Book");
            System.out.println("6.Delete Book");
            System.out.println("7.Exit");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            switch (choice) {

                case 1:
                    System.out.println("Enter id");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter title");
                    String title = sc.nextLine();
                    System.out.println("Enter author:");
                    String author = sc.nextLine();

                    System.out.println("Enter status:");
                    String status = sc.nextLine();
                    dao.addBook(id,
                            title,
                            author,
                            status);

                    break;
                case 2:
                    dao.viewBooks();
                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Enter title");
                    String search = sc.nextLine();
                    dao.searchBook(search);
                    break;
                case 4:
                    System.out.println("Enter Book ID");
                    int issueId = sc.nextInt();
                    dao.issueBook(issueId);
                    break;

                case 5:
                    System.out.println("Enter Book id");
                    int returnid = sc.nextInt();
                    dao.returnBook(returnid);
                    break;

                case 6:
                    System.out.println("Enter Book id");
                    int deleteId = sc.nextInt();
                    dao.deleteBook(deleteId);
                    break;

                case 7:
                    System.out.println("THANK YOU!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invaild Choice !");

            }
        }
    }
}