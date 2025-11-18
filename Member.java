import java.util.*;
import java.util.stream.Collectors;

public class Member {

    private Integer memberId;
    private String name;
    private String email;
    private List<Integer> issuedBooks = new ArrayList<>();

    public Member(Integer memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    // Getters
    public Integer getMemberId() { return memberId; }
    public List<Integer> getIssuedBooks() { return issuedBooks; }

    public void addIssuedBook(int bookId) {
        issuedBooks.add(bookId);
    }

    public void returnIssuedBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
    }

    public void displayMemberDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Issued Books: " + issuedBooks);
        System.out.println("-----------------------------------");
    }

    public String toCSV() {
        String list = String.join(";",
            issuedBooks.stream()
                       .map(String::valueOf)
                       .collect(Collectors.toList())
        );
        return memberId + "," + name + "," + email + "," + list;
    }

    public static Member fromCSV(String line) {
        String[] arr = line.split(",", 4);
        Member m = new Member(
            Integer.parseInt(arr[0]),
            arr[1],
            arr[2]
        );
        if (arr.length == 4 && !arr[3].isEmpty()) {
            for (String id : arr[3].split(";"))
                m.issuedBooks.add(Integer.parseInt(id));
        }
        return m;
    }
}