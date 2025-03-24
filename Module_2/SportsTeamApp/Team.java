public class Team {
    // Private fields
    private String teamName;
    private String[] players;
    private int playerCount;

    // Default constructor
    public Team(String teamName) {
        this.teamName = teamName;
        this.players = new String[20];  // Default size of 20
        this.playerCount = 0;
    }

    // Method to add a player to the team
    public void addPlayer(String player) {
        if (playerCount < players.length) {
            players[playerCount] = player;
            playerCount++;
        } else {
            System.out.println("The team is full! Cannot add more players.");
        }
    }

    // Method to get all players
    public String[] getPlayers() {
        return players;
    }

    // Method to get the number of players
    public int getPlayerCount() {
        return playerCount;
    }

    // Method to get the team name
    public String getTeamName() {
        return teamName;
    }
}
