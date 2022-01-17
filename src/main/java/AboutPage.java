import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AboutPage extends PerformIs {
    private final WebDriver driver;

    AboutPage(WebDriver driver) {
        this.driver = driver;
    }

    int returnPlayersInGame() {
        waitTo(2, "//div[@class = 'online_stat'][2]");
        String playersInGame = driver.findElement(By.xpath("//div[@class = 'online_stat'][2]")).getText();
        return returnInInt(playersInGame);
    }

    int returnPlayersInOnline() {
        waitTo(2, "//div[@class = 'online_stat'][1]");
        String playersInOnline = driver.findElement(By.xpath("//div[@class = 'online_stat'][1]")).getText();
        return returnInInt(playersInOnline);
    }

    boolean onlineComparison() {
        return returnPlayersInGame() < returnPlayersInOnline();
    }

    WebElement clickToMarket() {
        waitTo(2, "//a[@class = 'menuitem supernav' and contains(text(),'МАГАЗИН')]");
        return driver.findElement(By.xpath("//a[@class = 'menuitem supernav' and contains(text(),'МАГАЗИН')]"));
    }


}
