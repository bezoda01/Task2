import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends PerformIs {

    private By uniqueElement = By.id("home_maincap_v7");

    private By about = By.xpath("//a[@class = 'menuitem' and contains(text(),'О STEAM')]");

    private By newButton = By.xpath("//a[@class = 'pulldown_desktop' and contains(text(),'Новое и примечательное')]");

    private By leaderOfSells = By.xpath("//a[contains(text(),'Лидеры продаж') and @class = 'popup_menu_item']");

    private By buttonCommunity = By.xpath("//a[contains(text(),'СООБЩЕСТВО')]");

    private By commMarket = By.xpath("//a[contains(text(),'Торговая площадка')]");


    private final WebDriver driver;

    MainPage(WebDriver driver) {
        this.driver = driver;
    }

    void goToPage(String url) {
        driver.get(url);
    }

    void clickToAbout() {
        waitTo(uniqueElement, 5);
        findById(about).click();
    }

    boolean isThisPage() {
        waitTo(uniqueElement, 5);
        return findById(about).isEnabled();
    }

    boolean isThatMarket() {
        waitTo(uniqueElement, 5);
        return findById(uniqueElement).isEnabled();
    }

    void goCursorToNew() {
        Actions actions = new Actions(driver);
        actions.moveToElement(findByXpath(newButton)).release().build().perform();
    }

    void clickToLeader() {
        waitTo(5, leaderOfSells);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", findByXpath(leaderOfSells));
    }

    void moveToCommunity() {
        Actions actions = new Actions(driver);
        actions.moveToElement(findByXpath(buttonCommunity)).release().build().perform();

    }

    void clickToComMarket() {
        waitTo(5, commMarket);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", findByXpath(commMarket));
    }



}
