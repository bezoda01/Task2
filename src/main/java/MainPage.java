import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends PerformIs {

    private final By uniqueElement = By.id("home_maincap_v7");
    private final By about = By.xpath("//a[@class = 'menuitem' and contains(text(),'О STEAM')]");


    private final WebDriver driver;

    MainPage(WebDriver driver) {
        this.driver = driver;
    }

    void goToPage(String url) {
        driver.get(url);
    }

    WebElement clickToAbout() {
        waitTo(uniqueElement, 5);
        return driver.findElement(about);
    }

    WebElement isThatMarket() {
        waitTo(uniqueElement, 5);
        return driver.findElement(uniqueElement);
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
