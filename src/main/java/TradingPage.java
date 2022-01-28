import DataClasses.Filters;
import DataClasses.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import java.io.IOException;

public class TradingPage {

    private final WebDriver driver;
    PerformIs performIs = new PerformIs();

    TradingPage(WebDriver driver) {
        this.driver = driver;
    }

    private By search = By.id("advancedSearchBox");
    private By unique = By.id("tabMyListings");
    private By uniqueElement = By.xpath("//div[@class = 'title_text']");
    private By additionallyWindows = By.id("market_search_advanced_show");
    private By clickGameInPool = By.id("app_option_0_selected");
    private By firstLot = By.id("result_0_name");
    private By selectedHero = By.xpath("//select[@name='category_570_Hero[]']");
    private By heroName;
    private By rarityImortal;
    private By poolSearch = By.xpath("//div[@class = 'btn_medium btn_green_white_innerfade']/span");
    private By numbersResultBefore = By.id("searchResults_total");
    private By numbersResultAfter = By.id("searchResults_total");
    private By iconRemoveGolden = By.xpath("//*[contains(text(),'golden')]//span");
    private By iconRemoveDota = By.xpath("//a[@href][1]/span");
    private By firstLotName = By.id("result_0_name");
    private By heroInLot = By.xpath("//*[contains(text(),'Lifestealer')]//span/..");
    private By rarityInLot = By.xpath("//*[contains(text(),'Immortal')]//span/..");
    private By rarityInGame = By.id("largeiteminfo_item_type");
    private By nameHeroInLot = By.id("largeiteminfo_item_name");
    private By heroLot = By.xpath("//div[contains(text(),'Lifestealer')]");

    boolean checkIsOpen() {
        return performIs.findById(unique).isEnabled();
    }

    void openAdvancedOptions() {
        performIs.findByXpath(additionallyWindows).click();
    }

    boolean checkAdvOpIsOpen() {
        return performIs.findByXpath(uniqueElement).isEnabled();
    }

    void clickToGameChoice() {
        driver.findElement(clickGameInPool).click();
    }

    void choiceGame(String game) throws IOException {
        By temp = By.id(PerformIs.utilData().getString(game));
        performIs.findById(temp).click();
    }

    void clickToSelectHero() {
        performIs.waitTo(selectedHero);
        performIs.findByXpath(selectedHero).click();
    }

    void selectHero(String hero) throws IOException {
        heroName = By.xpath(PerformIs.utilData().getString(hero));
        performIs.waitTo(heroName);
        performIs.findByXpath(heroName).click();
    }

    void clickAndInput(String input) {

        Actions actions = new Actions(driver);
        actions.doubleClick(performIs.findById(search)).sendKeys(input).release().build().perform();
    }

    void clickRarityAndSearch(String rarity) throws IOException {
        rarityImortal = By.id(PerformIs.utilData().getString(rarity));
        Actions actions = new Actions(driver);
        actions.click(performIs.findById(rarityImortal)).release().build().perform();
        performIs.findByXpath(poolSearch).click();
    }

    Filters setFilterArr() {
        return new Filters(
                performIs.findByXpath("//a[@class = 'market_searchedForTerm' and text()][1]").getText(),
                performIs.findByXpath("//a[@class = 'market_searchedForTerm' and text()][2]").getText(),
                performIs.findByXpath("//a[@class = 'market_searchedForTerm' and text()][3]").getText(),
                performIs.findByXpath("//a[@class = 'market_searchedForTerm' and text()][4]").getText());
    }

    boolean methodInfoFirstFive() {
        performIs.waitTo(firstLotName);
        return performIs.infoFirstFiveGolden();
    }

    boolean removeIcons() {
        int numbersResultsBefore = Integer.parseInt(performIs.findById(numbersResultBefore).getText());
        performIs.findByXpath(iconRemoveGolden).click();

        performIs.findByXpath(iconRemoveDota).click();
        int numbersResultsAfter = Integer.parseInt(performIs.findById(numbersResultAfter).getText());

        return numbersResultsBefore != numbersResultsAfter;
    }

    Product setProductFirst() {
        return new Product(
                performIs.findById(firstLotName).getText(),
                performIs.findByXpath(heroInLot).getText(),
                performIs.findByXpath(rarityInLot).getText());
    }

    void clickToItem() {
        performIs.findById(firstLot).click();
    }

    Product setProductSecond() {
        performIs.waitTo(heroLot);
        return new Product(
                performIs.findById(nameHeroInLot).getText(),
                performIs.returnString(performIs.findById(heroLot).getText()),
                performIs.returnString2(performIs.findById(rarityInGame).getText()));
    }
}
