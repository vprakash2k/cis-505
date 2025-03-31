package CustomerAccountApp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
    // Private balance field
    private double balance = 200.00;

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Deposit method
    public void deposit(double amt) {
        if (amt > 0) {
            balance += amt;
        }
    }

    // Withdraw method
    public void withdraw(double amt) {
        if (amt <= balance && amt > 0) {
            balance -= amt;
        }
    }

    // Display menu method
    public void displayMenu() {
        System.out.println("1. Enter <D/d> for Deposit");
        System.out.println("2. Enter <W/w> for Withdraw");
        System.out.println("3. Enter <B/b> for Exit");
    }

    // Get transaction date
    public String getTransactionDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        return sdf.format(new Date());
    }
}
