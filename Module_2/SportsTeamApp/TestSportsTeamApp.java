import java.util.Scanner;

public class TestSportsTeamApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            // Prompt for team name
            System.out.print("Enter team name: ");
            String teamName = input.nextLine();

            // Create a new Team object
            Team team = new Team(teamName);

            // Prompt for player names
            System.out.print("Enter player names (separate by commas): ");
            String playersInput = input.nextLine();

            // Split the input string into an array of player names
            String[] players = playersInput.split(",");

            // Add players to the team
            for (String player : players) {
                team.addPlayer(player.trim()); // Trim spaces around the player names
            }

            // Display the results
            System.out.println("Number of players in team " + team.getTeamName() + ": " + team.getPlayerCount());
            System.out.print("Players on team: ");
            String[] teamPlayers = team.getPlayers();

            // Print the players in the team
            for (int i = 0; i < team.getPlayerCount(); i++) {
                if (i == team.getPlayerCount() - 1) {
                    System.out.print(teamPlayers[i]);  // Don't add a comma after the last player
                } else {
                    System.out.print(teamPlayers[i] + ", ");
                }
            }
            System.out.println(); // Move to the next line

            // Ask the user if they want to continue or exit
            System.out.print("Do you want to enter another team? (yes/no): ");
            String response = input.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                continueRunning = false;
            }
        }

        // Close the scanner to prevent resource leak
        input.close();
    }
}
