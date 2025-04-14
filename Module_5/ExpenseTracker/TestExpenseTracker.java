import java.util.*;
import java.io.IOException;

public class TestExpenseTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nWelcome to the Expense Tracker");
            System.out.println("\nMENU OPTION");
            System.out.println("\t 1. View Transactions");
            System.out.println("\t 2. Add Transactions");
            System.out.println("\t 3. View Expense");
            System.out.println("\t 4. Exit");

            int choice = ValidatorIO.getInt(sc, "\nPlease choose an option: ");

            switch (choice) {
                case 1:
                    try {
                        ArrayList<Transaction> all = TransactionIO.findAll();
                        for (Transaction t : all) {
                            System.out.println(t);
                            System.out.println("------------------------");
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading file: " + e.getMessage());
                    }
                    break;

                case 2:
                    boolean addMore = true;
                    ArrayList<Transaction> newTransactions = new ArrayList<>();

                    while (addMore) {
                        String date = ValidatorIO.getString(sc, "Enter date (MM-dd-yyyy): ");
                        String desc = ValidatorIO.getString(sc, "Enter description: ");
                        double amount = ValidatorIO.getDouble(sc, "Enter amount: ");
                        newTransactions.add(new Transaction(date, desc, amount));

                        String again = ValidatorIO.getString(sc, "Add another transaction? (y/n): ");
                        if (!again.equalsIgnoreCase("y")) {
                            addMore = false;
                        }
                    }

                    try {
                        TransactionIO.bulkInsert(newTransactions);
                        System.out.println("Transaction(s) added successfully!");
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        ArrayList<Transaction> all = TransactionIO.findAll();
                        double total = 0.0;
                        for (Transaction t : all) {
                            total += t.getAmount();
                        }
                        System.out.printf("Your total monthly expense is: $%,.2f%n", total);
                    } catch (IOException e) {
                        System.out.println("Error reading file: " + e.getMessage());
                    }
                    break;

                case 4:
                    keepRunning = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

            if (keepRunning) {
                String cont = ValidatorIO.getString(sc, "Continue? (y/n): ");
                if (!cont.equalsIgnoreCase("y")) {
                    keepRunning = false;
                }
            }
        }

        sc.close();
    }
}
