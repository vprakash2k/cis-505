import java.util.Scanner;

public class ValidatorIO {

    public static int getInt(Scanner sc, String prompt) {
        int input = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine(); // Clear newline
                isValid = true;
            } else {
                System.out.println("Error! Invalid integer value.");
                sc.nextLine(); // Discard invalid input
            }
        }
        return input;
    }

    public static double getDouble(Scanner sc, String prompt) {
        double input = 0.0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                input = sc.nextDouble();
                sc.nextLine();
                isValid = true;
            } else {
                System.out.println("Error! Invalid double value.");
                sc.nextLine(); // Clear the bad input
            }
        }
        return input;
    }

    public static String getString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
