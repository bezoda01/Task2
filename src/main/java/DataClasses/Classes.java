package DataClasses;

public class Classes {
    public Game gameFirst;
    public Game gameSecond;
    public Filters realFilters;
    public Filters expectedFilters = new Filters("Dota 2", "Lifestealer", "Immortal", "\"golden\"");

    public Game getGameFirst() {
        return gameFirst;
    }

    public void setGameFirst(Game gameFirst) {
        this.gameFirst = gameFirst;
    }

    public Game getGameSecond() {
        return gameSecond;
    }

    public void setGameSecond(Game gameSecond) {
        this.gameSecond = gameSecond;
    }

    public Filters getRealFilters() {
        return realFilters;
    }

    public void setRealFilters(Filters realFilters) {
        this.realFilters = realFilters;
    }

    public Product getBeforeProduct() {
        return beforeProduct;
    }

    public void setBeforeProduct(Product beforeProduct) {
        this.beforeProduct = beforeProduct;
    }

    public Product getAfterProduct() {
        return afterProduct;
    }

    public void setAfterProduct(Product afterProduct) {
        this.afterProduct = afterProduct;
    }

    public Product beforeProduct;
    public Product afterProduct;
}
