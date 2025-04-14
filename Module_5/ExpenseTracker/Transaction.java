import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String date;
    private String description;
    private double amount;

    // Default constructor
    public Transaction() {
        this.date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
        this.description = "";
        this.amount = 0.0;
    }

    // Argument constructor
    public Transaction(String date, String description, double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    // Accessors
    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    // Mutators
    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // toString
    @Override
    public String toString() {
        return "Date: " + date + "\nDescription: " + description + "\nAmount: $" + String.format("%.2f", amount);
    }
}
