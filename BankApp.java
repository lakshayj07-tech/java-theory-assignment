/**
 * BankApp.java
 * User Interface class that manages multiple accounts using an array.
 * Provides menu-driven operations: create, deposit, withdraw, view, update, exit.
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class BankApp {

    private Account[] accounts;
    private int count;
    private Scanner sc;
    private int nextAccountNumber;

    public BankApp() {
        accounts = new Account[10];
        count = 0;
        sc = new Scanner(System.in);
        nextAccountNumber = 1001;
    }

    // Expand array when full
    private void ensureCapacity() {
        if (count >= accounts.length) {
            int newSize = accounts.length * 2;
            Account[] larger = new Account[newSize];
            System.arraycopy(accounts, 0, larger, 0, accounts.length);
            accounts = larger;
        }
    }

    // Find account by number
    private Account findAccountByNumber(int accNum) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber() == accNum) {
                return accounts[i];
            }
        }
        return null;
    }

    // Create new account
    public void createAccount() {
        try {
            sc.nextLine();
            System.out.print("Enter account holder name: ");
            String name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Account creation aborted.");
                return;
            }

            System.out.print("Enter initial deposit amount: ");
            double initialDeposit = sc.nextDouble();
            if (initialDeposit < 0) {
                System.out.println("Initial deposit cannot be negative.");
                return;
            }

            sc.nextLine();
            System.out.print("Enter email address: ");
            String email = sc.nextLine().trim();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format.");
                return;
            }

            System.out.print("Enter phone number: ");
            String phone = sc.nextLine().trim();
            if (!isValidPhone(phone)) {
                System.out.println("Invalid phone number.");
                return;
            }

            ensureCapacity();
            Account acc = new Account(nextAccountNumber, name, initialDeposit, email, phone);
            accounts[count++] = acc;

            System.out.println("Account created successfully with Account Number: " + nextAccountNumber);
            nextAccountNumber++;

        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Account creation aborted.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    // Deposit money
    public void performDeposit() {
        try {
            System.out.print("Enter account number: ");
            int accNum = sc.nextInt();
            Account acc = findAccountByNumber(accNum);

            if (acc == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            if (acc.deposit(amount)) {
                System.out.println("Deposit successful. New balance: " + acc.getBalance());
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Deposit aborted.");
            sc.nextLine();
        }
    }

    // Withdraw money
    public void performWithdrawal() {
        try {
            System.out.print("Enter account number: ");
            int accNum = sc.nextInt();
            Account acc = findAccountByNumber(accNum);

            if (acc == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            if (acc.withdraw(amount)) {
                System.out.println("Withdrawal successful. New balance: " + acc.getBalance());
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Withdrawal aborted.");
            sc.nextLine();
        }
    }

    // View details
    public void showAccountDetails() {
        try {
            System.out.print("Enter account number: ");
            int accNum = sc.nextInt();
            Account acc = findAccountByNumber(accNum);

            if (acc == null) {
                System.out.println("Account not found.");
                return;
            }

            acc.displayAccountDetails();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            sc.nextLine();
        }
    }

    // Update email + phone
    public void updateContact() {
        try {
            sc.nextLine();
            System.out.print("Enter account number: ");
            int accNum = Integer.parseInt(sc.nextLine().trim());
            Account acc = findAccountByNumber(accNum);

            if (acc == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.print("Enter new email address: ");
            String email = sc.nextLine().trim();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format.");
                return;
            }

            System.out.print("Enter new phone number: ");
            String phone = sc.nextLine().trim();
            if (!isValidPhone(phone)) {
                System.out.println("Invalid phone number.");
                return;
            }

            acc.updateContactDetails(email, phone);
            System.out.println("Contact details updated successfully.");

        } catch (Exception e) {
            System.out.println("Error updating details: " + e.getMessage());
        }
    }

    // Email validation
    private boolean isValidEmail(String email) {
        int at = email.indexOf('@');
        int dot = email.lastIndexOf('.');
        return at > 0 && dot > at + 1 && dot < email.length() - 1;
    }

    // Phone validation
    private boolean isValidPhone(String phone) {
        if (phone.startsWith("+")) phone = phone.substring(1);
        if (phone.length() < 7 || phone.length() > 15) return false;
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    // Main Menu
    public void mainMenu() {
        while (true) {
            System.out.println("\n=== Banking Application ===");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: createAccount(); break;
                    case 2: performDeposit(); break;
                    case 3: performWithdrawal(); break;
                    case 4: showAccountDetails(); break;
                    case 5: updateContact(); break;
                    case 6:
                        System.out.println("Thank you for using the Banking Application. Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Enter 1–6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Numbers only.");
                sc.nextLine();
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        BankApp app = new BankApp();
        app.mainMenu();
    }
}
