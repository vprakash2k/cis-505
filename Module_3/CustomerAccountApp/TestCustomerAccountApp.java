package CustomerAccountApp;

import java.util.Scanner;

public class TestCustomerAccountApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt user for customer number
        System.out.print("Enter a customer number (1007, 1008, or 1009): ");
        int customerId = input.nextInt();

        // Get customer object
        Customer customer = CustomerDB.getCustomer(customerId);

        // Create account object
        Account account = new Account();

        // Display account menu
        int choice;
        do {
            account.displayMenu();
            System.out.print("Select an option: ");
            choice = input.nextInt();

            switch (choice) {
                case 1: // Deposit
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = input.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2: // Withdraw
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = input.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3: // Exit
                    System.out.println("Exiting the menu...");
                    break;
                default:
                    System.out.println("Error: Invalid Option.");
            }

            System.out.print("Would you like to continue (y/n)? ");
        } while (input.next().equalsIgnoreCase("y"));

        // Display customer details and account balance
        System.out.println("\nCustomer Details:\n" + customer);
        System.out.printf("Account Balance: $%,6.2f\n", account.getBalance());
        System.out.println("Transaction Date: " + account.getTransactionDate());
    }
}
