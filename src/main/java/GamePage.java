import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class GamePage extends PerformIs {

    private final WebDriver driver;

    GamePage(WebDriver driver) {
        this.driver = driver;
    }
    public ArrayList<String> infoAboutGameSecond;

    WebElement gameName() {
        return driver.findElement(By.id("appHubAppName"));
    }

    WebElement gameReleased() {
        return driver.findElement(By.xpath("//div[@class = 'date']"));
    }

    WebElement gamePrice() {
        return driver.findElement(By.xpath("//div[@data-price-final = '4699' and contains(text(),'USD')]"));
    }

    ArrayList<String> setInfoAboutGameSecond() {
        infoAboutGameSecond = new ArrayList<String>() {{
            add(gameName().getText());
            add(gameReleased().getText());
            add(correctPrice(gamePrice().getText()));
        }};
        return infoAboutGameSecond;
    }
}
