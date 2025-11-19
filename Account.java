/**
 * Account.java
 * Model class for a bank account.
 */

public class Account {

    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(int accountNumber, String accountHolderName, double initialDeposit, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Deposit method: amount must be positive
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        balance += amount;
        return true;
    }

    // Withdraw method: amount must be positive and <= balance
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance. Current balance: " + balance);
            return false;
        }
        balance -= amount;
        return true;
    }

    // Display current account details
    public void displayAccountDetails() {
        System.out.println("----- Account Details -----");
        System.out.println("Account Number     : " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Balance            : " + balance);
        System.out.println("Email              : " + email);
        System.out.println("Phone Number       : " + phoneNumber);
        System.out.println("----------------------------");
    }

    // Update contact details
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
