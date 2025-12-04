import java.util.Scanner;

public class ContactListApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactManager manager = new ContactManager();

        int choice;

        do {
            System.out.println("\n===== Contact List Application =====");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter contact name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    manager.addContact(new Contact(name, phone, email));
                    break;

                case 2:
                    System.out.print("Enter name of contact to remove: ");
                    String removeName = sc.nextLine();
                    manager.removeContact(removeName);
                    break;

                case 3:
                    System.out.print("Enter name to search: ");
                    String searchName = sc.nextLine();
                    Contact result = manager.searchContact(searchName);

                    if (result != null) {
                        System.out.println("Contact Found: " + result);
                    } else {
                        System.out.println("Contact Not Found!");
                    }
                    break;

                case 4:
                    manager.displayAllContacts();
                    break;

                case 5:
                    manager.saveContactsToFile("contacts.txt");
                    break;

                case 6:
                    manager.loadContactsFromFile("contacts.txt");
                    break;

                case 7:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}
