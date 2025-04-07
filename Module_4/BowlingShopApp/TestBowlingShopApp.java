import java.util.Scanner;

public class TestBowlingShopApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            displayMenu();
            input = scanner.nextLine();

            GenericQueue<Product> products = ProductDB.getProducts(input);

            while (products.size() > 0) {
                System.out.println(products.dequeue());
            }

        } while (!input.equals("x"));
    }

    public static void displayMenu() {
        System.out.println("MENU OPTIONS");
        System.out.println("1. <b> Bowling Balls");
        System.out.println("2. <s> Bowling Shoes");
        System.out.println("3. <a> Bowling Bags");
        System.out.println("4. <x> To Exit \n");
        System.out.println("Please choose an option:");
    }
}
