import com.fasterxml.jackson.core.JsonProcessingException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class AboutPage extends PerformIs {
    private final WebDriver driver;

    AboutPage(WebDriver driver) {
        this.driver = driver;
    }



    private By playersInGame = By.xpath("//div[@class = 'online_stat'][2]");
    private By playersInOnline = By.xpath("//div[@class = 'online_stat'][1]");
    private By buttonMarket = By.xpath("//a[@class = 'menuitem supernav' and contains(text(),'МАГАЗИН')]");


    boolean getPlayers() throws FileNotFoundException, JsonProcessingException {
        waitTo(2, playersInGame);
        waitTo(2, playersInOnline);
        return onlineComparison(findByXpath(playersInGame).getText(), findByXpath(playersInOnline).getText());
    }


    void clickToMarket() {
        waitTo(2, buttonMarket);
        findByXpath(buttonMarket).click();
    }


}
