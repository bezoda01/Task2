import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends PerformIs {

    private By uniqueElement = By.id("home_maincap_v7");

    private By about = By.xpath("//a[contains(text(),'STEAM')]");

    private By newButton = By.id("noteworthy_tab");

    private By leaderOfSells = By.cssSelector("a[href*='topsellers'].popup_menu_item");

    private By buttonCommunity = By.xpath("//div[@class = 'supernav_container']//a[@data-tooltip-content = '.submenu_community']");

    private  By commMarket = By.cssSelector("a.submenuitem[href*='market']");


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
