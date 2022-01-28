import DataClasses.Game;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TopSellersPage {
    private final WebDriver driver;
    PerformIs util = new PerformIs();


    TopSellersPage(WebDriver driver) {
        this.driver = driver;
    }

    private By blockOs = By.id("os");
    private By blockNunber = By.id("category3");
    private By unique = By.id("additional_search_options");
    private By blockLabels = By.id("TagFilter_Container");
    private By checkBoxLabel = By.xpath("//span[contains(@class, 'checked') and @data-value = '19']");
    private By checkBoxOs = By.xpath("//span[contains(@class, 'checked') and @data-value = 'linux']");
    private By checkBoxCoop = By.xpath("//span[contains(@class, 'checked') and @data-value = '48']");
    private By firstGameInList = By.xpath("//div[@id = 'search_resultsRows']//a[1]");
    private By firstGameTitle = By.xpath("//a[1]//div//div//span[@class = 'title']");
    private By firstGameReleased = By.xpath("//a[1]//div//div[contains(@class,'released')]");
    private By firstGamePrice = By.xpath("//a[1]//div//div[contains(@class,'price')]//div[contains(@class,'price')]");
    private By osType = By.xpath("//div[@data-collapse-name = 'os']/div[@class = 'block_header']");
    private By osCheckBox = By.xpath("//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']");
    private By coopType = By.xpath("//div[@data-collapse-name = 'category3']/div[@class= 'block_header']");
    private By coopCheckBox = By.xpath("//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']");
    private By labelsType = By.xpath("//div[@data-collapse-name = 'tags']/div[@class = 'block_header labs_block_header']");
    private By labelsCheckBox = By.xpath("//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']");
    private By gamesOnDemand = By.xpath("//div[@class = 'search_results_count']");
    private By gamesNum = By.xpath("//a[@data-gpnav = 'item']");


    boolean checkPage() {
        return util.findById(unique).isEnabled();
    }

    void checkBlockOsIsOpen() {

        if (!util.findById(blockOs).isEnabled()) {
            util.findByXpath(osType).click();
            util.waitTo(osCheckBox);
            util.findByXpath(osCheckBox).click();
        } else {
            util.waitTo(osCheckBox);
            util.findByXpath(osCheckBox).click();
        }
    }

    void checkBlockCoopIsOpen() {

        if (!util.findById(blockNunber).isEnabled()) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", util.findByXpath(coopType));
            util.waitTo(coopCheckBox);
            executor.executeScript("arguments[0].click();", util.findByXpath(coopCheckBox));

        } else {
            util.waitTo(coopCheckBox);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", util.findByXpath(coopCheckBox));
        }
    }

    void checkBlockLabels() {


        if (!util.findById(blockLabels).isEnabled()) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", labelsType);
            util.waitTo(labelsCheckBox);
            executor.executeScript("arguments[0].click();", util.findByXpath(labelsCheckBox));
        } else {
            util.waitTo(labelsCheckBox);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", util.findByXpath(labelsCheckBox));
        }
    }


    boolean checkBoxLabels() {
        return util.findByXpath(checkBoxLabel).isEnabled();
    }

    boolean checkBoxOs() {
        return util.findByXpath(checkBoxOs).isEnabled();
    }

    boolean checkBoxCoop() {
        return util.findByXpath(checkBoxCoop).isEnabled();
    }

    boolean checkNumResult() {
        util.waitTo(gamesOnDemand);
        List<WebElement> element = driver.findElements(gamesNum);
        util.waitTo(gamesNum);
        return util.parsToIntFirst(util.findByXpath(gamesOnDemand).getText(), String.valueOf(element.size()));
    }

    public Game returnGameInfoFirst() {
        return new Game(
                util.findByXpath(firstGameTitle).getText(),
                util.findByXpath(firstGameReleased).getText(),
                util.findByXpath(firstGamePrice).getText());
    }

    void firstGame() {

        util.findByXpath(firstGameInList).click();
    }


}
