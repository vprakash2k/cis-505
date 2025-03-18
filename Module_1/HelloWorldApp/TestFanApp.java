public class TestFanApp {
    public static void main(String[] args) {
        // Create a fan object using the default constructor
        Fan fan1 = new Fan();

        // Create a fan object using the argument constructor
        Fan fan2 = new Fan(Fan.FAST, true, 8.0, "Blue");

        // Display the fan objects using toString() method
        System.out.println(fan1.toString());
        System.out.println(fan2.toString());
    }
}
