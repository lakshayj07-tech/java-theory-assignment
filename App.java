import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== City Library Digital Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1: lm.addBook(); break;
                case 2: lm.addMember(); break;
                case 3: lm.issueBook(); break;
                case 4: lm.returnBook(); break;
                case 5: lm.searchBooks(); break;
                case 6: lm.sortBooks(); break;
                case 7:
                    lm.saveToFile();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}