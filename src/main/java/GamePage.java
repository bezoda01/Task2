import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class GamePage extends PerformIs {

    private final WebDriver driver;

    GamePage(WebDriver driver) {
        this.driver = driver;
    }

    public ArrayList<String> infoAboutGameSecond;

    private By nameGame = By.id("appHubAppName");

    private By gameReleased = By.xpath("//div[@class = 'date']");

    private By gamePrice = By.xpath("//div[@class = 'game_purchase_price price']");

    String gameName() {
        return findById(nameGame).getText();
    }

   void setInfoAboutGameSecond() {
        infoAboutGameSecond = new ArrayList<String>() {{
            add(findById(nameGame).getText());
            add(findByXpath(gameReleased).getText());
            add(correctPrice(findByXpath(gamePrice).getText()));
        }};
    }
}
