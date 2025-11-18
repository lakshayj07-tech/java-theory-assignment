import java.io.*;
import java.util.*;

public class LibraryManager {

    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();

    private final String BOOK_FILE = "books.txt";
    private final String MEMBER_FILE = "members.txt";

    private Scanner sc = new Scanner(System.in);

    public LibraryManager() {
        loadFromFile();
    }

    // Load data
    public void loadFromFile() {
        try {
            File bFile = new File(BOOK_FILE);
            File mFile = new File(MEMBER_FILE);
            bFile.createNewFile();
            mFile.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(bFile));
            String line;
            while ((line = br.readLine()) != null) {
                Book b = Book.fromCSV(line);
                books.put(b.getBookId(), b);
            }
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader(mFile));
            while ((line = br2.readLine()) != null) {
                Member m = Member.fromCSV(line);
                members.put(m.getMemberId(), m);
            }
            br2.close();
        }
        catch (Exception e) {
            System.out.println("Error loading files: " + e.getMessage());
        }
    }

    // Save data
    public void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(BOOK_FILE));
            for (Book b : books.values()) bw.write(b.toCSV() + "\n");
            bw.close();

            BufferedWriter bw2 = new BufferedWriter(new FileWriter(MEMBER_FILE));
            for (Member m : members.values()) bw2.write(m.toCSV() + "\n");
            bw2.close();
        }
        catch (Exception e) {
            System.out.println("Error saving files: " + e.getMessage());
        }
    }

    // Add book
    public void addBook() {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        int id = books.size() + 100;
        Book b = new Book(id, title, author, category, false);

        books.put(id, b);
        saveToFile();

        System.out.println("Book added with ID: " + id);
    }

    // Add member
    public void addMember() {
        System.out.print("Enter Member Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        int id = members.size() + 1000;
        Member m = new Member(id, name, email);
        members.put(id, m);
        saveToFile();

        System.out.println("Member added with ID: " + id);
    }

    // Issue book
    public void issueBook() {
        System.out.print("Enter Book ID: ");
        int bookId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Member ID: ");
        int memberId = Integer.parseInt(sc.nextLine());

        Book b = books.get(bookId);
        Member m = members.get(memberId);

        if (b == null || m == null) {
            System.out.println("Invalid book or member ID.");
            return;
        }

        if (b.getIsIssued()) {
            System.out.println("Book already issued!");
            return;
        }

        b.markAsIssued();
        m.addIssuedBook(bookId);
        saveToFile();
        System.out.println("Book issued successfully!");
    }

    // Return book
    public void returnBook() {
        System.out.print("Enter Book ID: ");
        int bookId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Member ID: ");
        int memberId = Integer.parseInt(sc.nextLine());

        Book b = books.get(bookId);
        Member m = members.get(memberId);

        if (b == null || m == null) {
            System.out.println("Invalid book or member ID.");
            return;
        }

        b.markAsReturned();
        m.returnIssuedBook(bookId);
        saveToFile();

        System.out.println("Book returned successfully!");
    }

    // Search
    public void searchBooks() {
        System.out.print("Search by (title/author/category): ");
        String key = sc.nextLine().toLowerCase();

        System.out.print("Enter value: ");
        String value = sc.nextLine().toLowerCase();

        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(value) ||
                b.getAuthor().toLowerCase().contains(value) ||
                b.getCategory().toLowerCase().contains(value)) {
                b.displayBookDetails();
            }
        }
    }

    // Sorting
    public void sortBooks() {
        List<Book> list = new ArrayList<>(books.values());

        System.out.println("Sort by: 1) Title 2) Author 3) Category");
        int c = Integer.parseInt(sc.nextLine());

        if (c == 1) Collections.sort(list);
        if (c == 2) list.sort(Book.sortByAuthor);
        if (c == 3) list.sort(Book.sortByCategory);

        for (Book b : list) b.displayBookDetails();
    }
}