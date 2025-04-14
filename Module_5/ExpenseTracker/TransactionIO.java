import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionIO {
    private static final String FILE_NAME = "expenses.txt";
    private static File file = new File(FILE_NAME);

    public static void bulkInsert(ArrayList<Transaction> transactions) throws IOException {
        PrintWriter output = null;
        if (file.exists()) {
            output = new PrintWriter(new FileOutputStream(file, true)); // Append mode
        } else {
            output = new PrintWriter(new FileWriter(FILE_NAME));
        }

        for (Transaction t : transactions) {
            output.println(t.getDate());
            output.println(t.getDescription());
            output.println(t.getAmount());
        }

        output.close();
    }

    public static ArrayList<Transaction> findAll() throws IOException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        if (!file.exists()) {
            return transactions;
        }

        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            String date = input.nextLine();
            String description = input.nextLine();
            double amount = Double.parseDouble(input.nextLine());
            transactions.add(new Transaction(date, description, amount));
        }

        input.close();
        return transactions;
    }
}
