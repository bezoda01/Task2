import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class MainPage {

    private final WebDriver driver;
    PerformIs util = new PerformIs();

    MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By uniqueElement = By.id("store_nav_area");
    private By about = By.cssSelector(".menuitem[href*='about']");
    private By newButton = By.id("noteworthy_tab");
    private By leaderOfSells = By.cssSelector("a[href*='topsellers'].popup_menu_item");
    private By buttonCommunity = By.xpath("//div[@class = 'supernav_container']//a[@data-tooltip-content = '.submenu_community']");
    private  By commMarket = By.cssSelector("a.submenuitem[href*='market']");

    void goToPage(String url) {
        driver.get(url);
    }

    void clickToAbout() {
        util.findByXpath(about).click();
    }

    boolean isThisPage() {
        return util.findByXpath(about).isEnabled();
    }

    boolean isThatMarket() {
        return util.findById(uniqueElement).isEnabled();
    }

    void goCursorToNew() {
        Actions actions = new Actions(driver);
        actions.moveToElement(util.findByXpath(newButton)).release().build().perform();
    }

    void clickToLeader() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", util.findByXpath(leaderOfSells));
    }

    void moveToCommunity() {
        Actions actions = new Actions(driver);
        actions.moveToElement(util.findByXpath(buttonCommunity)).release().build().perform();

    }

    void clickToComMarket() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", util.findByXpath(commMarket));
    }
}
