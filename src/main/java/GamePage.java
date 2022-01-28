import DataClasses.Classes;
import DataClasses.Game;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class GamePage {
    Classes classes = new Classes();

    private final WebDriver driver;
    PerformIs util = new PerformIs();

    GamePage(WebDriver driver) {
        this.driver = driver;
    }

    private By nameGame = By.id("appHubAppName");
    private By gameReleased = By.xpath("//div[@class = 'date']");
    private By gamePrice = By.xpath("//div[@class = 'game_purchase_price price']");

    String gameName() {
        return util.findById(nameGame).getText();
    }


    Game returnGameInfoSecond() {

        return new Game(
                util.findById(nameGame).getText(),
                util.findByXpath(gameReleased).getText(),
                util.correctPrice(util.findByXpath(gamePrice).getText()));
    }
}
