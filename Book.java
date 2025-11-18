import java.io.*;

public class Book implements Comparable<Book> {

    private Integer bookId;
    private String title;
    private String author;
    private String category;
    private Boolean isIssued;

    public Book(Integer bookId, String title, String author, String category, Boolean isIssued) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = isIssued;
    }

    // Getters
    public Integer getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public Boolean getIsIssued() { return isIssued; }

    public void markAsIssued() { this.isIssued = true; }
    public void markAsReturned() { this.isIssued = false; }

    public void displayBookDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Issued: " + (isIssued ? "Yes" : "No"));
        System.out.println("-----------------------------------");
    }

    // Sorting by Title (Comparable)
    @Override
    public int compareTo(Book o) {
        return this.title.compareToIgnoreCase(o.title);
    }

    // Sort by Author (Comparator)
    public static java.util.Comparator<Book> sortByAuthor =
        (b1, b2) -> b1.author.compareToIgnoreCase(b2.author);

    // Sort by Category
    public static java.util.Comparator<Book> sortByCategory =
        (b1, b2) -> b1.category.compareToIgnoreCase(b2.category);

    // Convert to CSV
    public String toCSV() {
        return bookId + "," + title + "," + author + "," + category + "," + isIssued;
    }

    // Parse CSV
    public static Book fromCSV(String line) {
        String[] arr = line.split(",");
        return new Book(
            Integer.parseInt(arr[0]),
            arr[1],
            arr[2],
            arr[3],
            Boolean.parseBoolean(arr[4])
        );
    }
}