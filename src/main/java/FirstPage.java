import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.Arrays;

public class FirstPage extends Singleton {

    private static WebDriver driver;

    FirstPage(WebDriver driver) {
        FirstPage.driver = driver;
    }

    void quit() {
        getDriver().quit();
    }

    void goToPage() {
        getDriver().get("https://store.steampowered.com");
    }

    WebElement clickToAbout() {
        waitTo(2, "//a[contains(text(),'STEAM')]");
        return getDriver().findElement(By.xpath("//a[contains(text(),'STEAM')]"));
    }

    int returnPlayersInGame() {
        waitTo(2, "//div[@class = 'online_stat'][2]");
        String playersInGame = getDriver().findElement(By.xpath("//div[@class = 'online_stat'][2]")).getText();
        return returnInInt(playersInGame);
    }

    int returnPlayersInOnline() {
        waitTo(2, "//div[@class = 'online_stat'][1]");
        String playersInOnline = getDriver().findElement(By.xpath("//div[@class = 'online_stat'][1]")).getText();
        return returnInInt(playersInOnline);
    }

    boolean onlineComparison() {
        return returnPlayersInGame() < returnPlayersInOnline();
    }

    WebElement clickToMarket() {
        waitTo(2, "//a[@class = 'menuitem supernav' and contains(text(),'МАГАЗИН')]");
        return getDriver().findElement(By.xpath("//a[@class = 'menuitem supernav' and contains(text(),'МАГАЗИН')]"));
    }

    WebElement isThatMarket() {
        waitTo(2, "//a[contains(text(),'Ваш магазин')]");
        return getDriver().findElement(By.xpath("//a[contains(text(),'Ваш магазин')]"));
    }
}
