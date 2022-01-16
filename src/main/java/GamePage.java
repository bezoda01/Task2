import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class GamePage extends Singletone {
    public ArrayList<String> infoAboutGameSecond;

    WebElement gameName() {
        return getDriver().findElement(By.id("appHubAppName"));
    }

    WebElement gameReleased() {
        return getDriver().findElement(By.xpath("//div[@class = 'date']"));
    }

    WebElement gamePrice() {
        return getDriver().findElement(By.xpath("//div[@data-price-final = '4699' and contains(text(),'USD')]"));
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
