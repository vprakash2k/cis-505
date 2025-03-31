package CustomerAccountApp;

public class CustomerDB {
    public static Customer getCustomer(int id) {
        if (id == 1007) {
            return new Customer("Prakash V", "763 Widgeon Dr", "Wheeling", "60036");
        } else if (id == 1008) {
            return new Customer("Jennifer Smith", "8422 Grover Ave,", "Bellevue", "68123");
        } else if (id == 1009) {
            return new Customer("Alice Johnson", "2000 LakeCook Rd", "Riverwoods", "600123");
        } else {
            return new Customer(); // Return a default customer if no match
        }
    }
}
