public class Ball extends Product {
    private String color = "";

    public Ball() {}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() + "\nColor: " + color;
    }
}
