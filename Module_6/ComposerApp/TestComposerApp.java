import java.util.List;
import java.util.Scanner;

public class TestComposerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemComposerDao dao = new MemComposerDao();

        boolean running = true;

        while (running) {
            System.out.println("\n Welcome to the Composer App");
            System.out.println("\n MENU OPTIONS");
            System.out.println("\t 1. View Composers");
            System.out.println("\t 2. Find Composer");
            System.out.println("\t 3. Add Composer");
            System.out.println("\t 4. Exit");
            System.out.print("\n Please choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (option) {
                case 1:
                    List<Composer> composers = dao.findAll();
                    for (Composer composer : composers) {
                        System.out.println("\n" + composer);
                    }
                    break;

                case 2:
                    System.out.print("Enter composer ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    Composer composer = dao.findBy(id);
                    if (composer != null) {
                        System.out.println("\n" + composer);
                    } else {
                        System.out.println("Composer not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter composer ID: ");
                    int newId = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer

                    System.out.print("Enter composer name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter composer genre: ");
                    String genre = scanner.nextLine();

                    dao.insert(new Composer(newId, name, genre));
                    System.out.println("Composer added successfully.");
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}
