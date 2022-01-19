import com.fasterxml.jackson.core.JsonProcessingException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TradingPage extends PerformIs {

    private final WebDriver driver;

    TradingPage(WebDriver driver) {
        this.driver = driver;
    }

    private By search = By.id("advancedSearchBox");

    private By GameInPool = By.id("app_option_570");

    private By clickGameInPool = By.id("app_option_0_selected");

    private By firstLot = By.id("result_0_name");

    private By selectedHero = By.xpath("//select[@name='category_570_Hero[]']");

    private By heroLifestealer = By.xpath("//option[@value = 'tag_npc_dota_hero_life_stealer']");

    private By rarityImortal = By.id("tag_570_Rarity_Rarity_Immortal");

    private By poolSearch = By.xpath("//div[@onclick]//span[contains(text(),'Поиск')]");

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

    private ArrayList<String> firstFive = new ArrayList<>();

    ArrayList<String> filter;
    int numbersResultsBefore;
    int numbersResultsAfter;
    String secondItemName;
    String secondItemHero;
    String getSecondItemRarity;
    ArrayList<String> filterItemBefore;
    ArrayList<String> filterItemAfter;


    boolean checkIsOpen() {
        return findByXpath("//span[contains(text(),'Активные')]").isEnabled();
    }

    void openAdvancedOptions() {
        findByXpath("//span[contains(text(),'Дополнительные')]").click();
    }

    boolean checkAdvOpIsOpen() {
        return findByXpath("//div[contains(text(),'Поиск')]").isEnabled();
    }

    void clickToGameChoice() {
        driver.findElement(clickGameInPool).click();
    }

    void choiceGame() {
        driver.findElement(GameInPool).click();
    }

    void clickToSelectHero() {
        waitTo(5, selectedHero);
        findByXpath(selectedHero).click();
    }

    void selectLifeStealer() {
        waitTo(5, heroLifestealer);
        findByXpath(heroLifestealer).click();
    }

    void clickAndInput(String input) {
        Actions actions = new Actions(driver);
        actions.doubleClick(findById(search)).sendKeys(input).release().build().perform();
    }

    void clickRarityAndSearch() {

        Actions actions = new Actions(driver);
        actions.click(findById(rarityImortal)).release().build().perform();
        findByXpath(poolSearch).click();
    }

    void setFilterArr() {
        filter = new ArrayList<String>() {{
            add("//*[contains(text(),'Dota 2')]");
            add("//*[contains(text(),'Lifestealer')]");
            add("//*[contains(text(),'Immortal')]");
            add("//*[contains(text(),'golden')]");
        }};

    }

    boolean methodInfoFirstFive() throws FileNotFoundException, JsonProcessingException {
        waitTo(By.id("result_0_name"), 5);
        return util().infoFirstFiveGolden(firstFive);
    }

    boolean returnElement(String element) {
        return findByXpath(element).isEnabled();
    }

    void removeIcons() {
        numbersResultsBefore = Integer.parseInt(findById(numbersResultBefore).getText());
        findByXpath(iconRemoveGolden).click();

        findByXpath(iconRemoveDota).click();
        numbersResultsAfter = Integer.parseInt(findById(numbersResultAfter).getText());
    }

    void clickToItem() {
        filterItemBefore = new ArrayList<String>() {{
            add(findById(firstLotName).getText());
            add(findByXpath(heroInLot).getText());
            add(findByXpath(rarityInLot).getText());
        }};
        findById(firstLot).click();
    }

    void getInfoAboutItem() throws FileNotFoundException, JsonProcessingException {

        waitTo(5, heroLot);
        secondItemHero = util().returnString(findById(heroLot).getText());
        getSecondItemRarity = util().returnString2(findById(rarityInGame).getText());
        secondItemName = findById(nameHeroInLot).getText();
        filterItemAfter = new ArrayList<String>() {{
            add(secondItemName);
            add(secondItemHero);
            add(getSecondItemRarity);
        }};
    }


}
