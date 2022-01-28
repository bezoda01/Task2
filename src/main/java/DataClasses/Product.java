package DataClasses;

public class Product {
    private String name;
    private String hero;
    private String rarity;

    public String getName() {
        return name;
    }

    public String getHero() {
        return hero;
    }

    public String getRarity() {
        return rarity;
    }

    public Product(String name, String hero, String rarity) {
        this.name = name;
        this.hero = hero;
        this.rarity = rarity;
    }
}
