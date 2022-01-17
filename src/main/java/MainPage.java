import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends PerformIs {

    private final WebDriver driver;

    MainPage(WebDriver driver) {
        this.driver = driver;
    }

    void goToPage() {
        driver.get("https://store.steampowered.com");
    }

    WebElement clickToAbout() {
        waitTo(5, "//a[contains(text(),'О STEAM')]");
        return driver.findElement(By.xpath("//a[contains(text(),'О STEAM')]"));
    }

    WebElement isThatMarket() {
        waitTo(2, "//a[contains(text(),'Ваш магазин')]");
        return driver.findElement(By.xpath("//a[contains(text(),'Ваш магазин')]"));
    }

    void goCursorToNew() {
        WebElement button = driver.findElement(By.xpath("//a[@class = 'pulldown_desktop' and contains(text(),'Новое и примечательное')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).release().build().perform();
    }

    void clickToLeader() {
        waitTo(5, "//a[contains(text(),'Лидеры продаж') and @class = 'popup_menu_item']");
        WebElement buttonLeader = driver.findElement(By.xpath("//a[contains(text(),'Лидеры продаж') and @class = 'popup_menu_item']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", buttonLeader);
    }

    void moveToCommunity() {
        WebElement community = driver.findElement(By.xpath("//a[contains(text(),'СООБЩЕСТВО')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(community).release().build().perform();

    }

    void clickToComMarket() {
        waitTo(5, "//a[contains(text(),'Торговая площадка')]");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        WebElement market = driver.findElement(By.xpath("//a[contains(text(),'Торговая площадка')]"));
        executor.executeScript("arguments[0].click();", market);
    }



}
