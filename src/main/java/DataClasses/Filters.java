package DataClasses;

public class Filters {
    private String game;
    private String hero;
    private String rarity;
    private String input;

    public String getGame() {
        return game;
    }

    public String getHero() {
        return hero;
    }

    public String getRarity() {
        return rarity;
    }

    public String getInput() {
        return input;
    }

    public Filters(String game, String hero, String rarity, String input) {
        this.game = game;
        this.hero = hero;
        this.rarity = rarity;
        this.input = input;
    }
}
