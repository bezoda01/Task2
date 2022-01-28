package DataClasses;

public class Game {
    private String name;
    private String gameReleased;
    private String price;

    public String getName() {
        return name;
    }

    public String getGameReleased() {
        return gameReleased;
    }

    public String getPrice() {
        return price;
    }

    public Game(String name, String gameReleased, String price) {
        this.name = name;
        this.gameReleased = gameReleased;
        this.price = price;
    }
}
