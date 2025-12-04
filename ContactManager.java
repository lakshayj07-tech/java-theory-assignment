import java.io.*;
import java.util.*;

public class ContactManager {

    private HashMap<String, Contact> contactList = new HashMap<>();

    // Add Contact
    public void addContact(Contact contact) {
        contactList.put(contact.getName(), contact);
        System.out.println("Contact added successfully.");
    }

    // Remove Contact
    public void removeContact(String name) {
        if (contactList.containsKey(name)) {
            contactList.remove(name);
            System.out.println("Contact removed successfully.");
        } else {
            System.out.println("Contact not found!");
        }
    }

    // Search Contact
    public Contact searchContact(String name) {
        return contactList.get(name);
    }

    // Display All Contacts
    public void displayAllContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("\n===== Contact List =====");
        for (Contact c : contactList.values()) {
            System.out.println(c);
        }
        System.out.println("========================\n");
    }

    // Save contacts to file
    public void saveContactsToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Contact c : contactList.values()) {
                bw.write(c.getName() + "," + c.getPhoneNumber() + "," + c.getEmail());
                bw.newLine();
            }
            System.out.println("Contacts saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    // Load contacts from file
    public void loadContactsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            contactList.clear(); // Clear old data
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Contact contact = new Contact(data[0], data[1], data[2]);
                    contactList.put(data[0], contact);
                }
            }
            System.out.println("Contacts loaded from file successfully.");
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }
}
