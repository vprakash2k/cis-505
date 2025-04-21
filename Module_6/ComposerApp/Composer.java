public class Composer {
    private int id;
    private String name;
    private String genre;

    // No-argument constructor
    public Composer() {
        this.id = 0;
        this.name = "";
        this.genre = "";
    }

    // Parameterized constructor
    public Composer(int id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    // Accessor methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    // toString method override
    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nGenre: " + genre;
    }
}
