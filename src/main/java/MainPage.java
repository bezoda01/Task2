import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends Singletone {

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

    void goCursorToNew() {
        WebElement button = getDriver().findElement(By.xpath("//a[@class = 'pulldown_desktop' and contains(text(),'Новое и примечательное')]"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(button).release().build().perform();
    }

    void clickToLeader() {
        waitTo(5, "//a[contains(text(),'Лидеры продаж') and @class = 'popup_menu_item']");
        WebElement buttonLeader = getDriver().findElement(By.xpath("//a[contains(text(),'Лидеры продаж') and @class = 'popup_menu_item']"));
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].click();", buttonLeader);
    }



}
