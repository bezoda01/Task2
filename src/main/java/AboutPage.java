import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AboutPage {
    private final WebDriver driver;
    PerformIs util = new PerformIs();

    AboutPage(WebDriver driver) {
        this.driver = driver;
    }


    private By buttonMarket = By.cssSelector("a[href*='global-header'].menuitem.supernav");
    private By playersOnline = By.xpath("//div [contains (@class, 'gamers_online')]/parent::div");
    private By playersInGames = By.xpath("//div [contains (@class, 'gamers_in_game')]/parent::div");


    boolean getPlayers() {
        util.waitTo(2, playersOnline);
        util.waitTo(2, playersInGames);
        return util.onlineComparison(util.findByXpath(playersInGames).getText(), util.findByXpath(playersOnline).getText());
    }


    void clickToMarket() {
        util.waitTo(2, buttonMarket);
        util.findByXpath(buttonMarket).click();
    }


}
