import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.Arrays;

public class MarketPage extends Singleton {

    public MarketPage() {
    }

    void quit() {
        getDriver().quit();
    }

    void goToPage() {
        getDriver().get("https://store.steampowered.com");
    }

    WebElement clickToAbout() {
        waitTo(5, "//a[contains(text(),'О STEAM')]");
        return getDriver().findElement(By.xpath("//a[contains(text(),'STEAM')]"));
    }

    WebElement isThatMarket() {
        waitTo(2, "//a[contains(text(),'Ваш магазин')]");
        return getDriver().findElement(By.xpath("//a[contains(text(),'Ваш магазин')]"));
    }
}
