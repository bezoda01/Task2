import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

import java.util.Collections;

public class TradingPage extends PerformIs {

    private final WebDriver driver;

    TradingPage(WebDriver driver) {
        this.driver = driver;
    }

    private By search = By.id("advancedSearchBox");

    ArrayList<String> firstFive = new ArrayList<>();
    ArrayList<String> filter;
    int numbersResultsBefore;
    int numbersResultsAfter;
    WebElement firstItemName;
    String secondItemName;
    String secondItemHero;
    String getSecondItemRarity;
    ArrayList<String> filterItemBefore;
    ArrayList<String> filterItemAfter;



    WebElement checkIsOpen() {
        return driver.findElement(By.xpath("//span[contains(text(),'Активные')]"));
    }

    WebElement openAdvancedOptions() {
        return driver.findElement(By.xpath("//span[contains(text(),'Дополнительные')]"));
    }

    WebElement checkAdvOpIsOpen() {
        return driver.findElement(By.xpath("//div[contains(text(),'Поиск')]"));
    }

    WebElement clickToGameChoice() {
        return driver.findElement(By.id("app_option_0_selected"));
    }

    WebElement choiceGame() {
        return driver.findElement(By.id("app_option_570"));
    }

    void clickToSelectHero() {
        waitTo(5, "//select[@name='category_570_Hero[]']");
        WebElement selectHero = driver.findElement(By.xpath("//select[@name='category_570_Hero[]']"));
        selectHero.click();
    }

    void selectLifeStealer() {
        waitTo(5, "//option[@value = 'tag_npc_dota_hero_life_stealer']");
        WebElement lifeStealer = driver.findElement(By.xpath("//option[@value = 'tag_npc_dota_hero_life_stealer']"));
        lifeStealer.click();
    }

    void clickAndInput(String input) {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(search)).sendKeys(input).release().build().perform();
    }

    void clickRarityAndSearch() {
        WebElement immortal = driver.findElement(By.id("tag_570_Rarity_Rarity_Immortal"));
        Actions actions = new Actions(driver);
        actions.click(immortal).release().build().perform();
        clickSearch().click();
    }

    WebElement clickSearch() {
        return driver.findElement(By.xpath("//div[@onclick]//span[contains(text(),'Поиск')]"));
    }

    void setFilterArr() {
        filter = new ArrayList<String>(){{
            add("//*[contains(text(),'Dota 2')]");
            add("//*[contains(text(),'Lifestealer')]");
            add("//*[contains(text(),'Immortal')]");
            add("//*[contains(text(),'golden')]");
        }};

    }

    boolean methodInfoFirstFive() {

        int counter = 0;
        for (int i = 0; i < 5; i++) {
            String temp = driver.findElement(By.id("result_" + i + "_name")).getText();
            String[] tempMass = temp.split(" ");
            Collections.addAll(firstFive, tempMass);
        }
        for (String s : firstFive) {
            if(s.equals("Golden")) {
                counter++;
            }
        }
        return counter == 5;
    }

    WebElement returnElement(String element) {
        return driver.findElement(By.xpath(element));
    }

    void removeIcons() {
        numbersResultsBefore = Integer.parseInt(driver.findElement(By.id("searchResults_total")).getText());
        WebElement goldenRemove = driver.findElement(By.xpath("//*[contains(text(),'golden')]//span"));
        goldenRemove.click();
        WebElement dotaRemove = driver.findElement(By.xpath("//a[@href][1]/span"));
        dotaRemove.click();
        numbersResultsAfter = Integer.parseInt(driver.findElement(By.id("searchResults_total")).getText());
    }

    WebElement clickToItem() {
        filterItemBefore = new ArrayList<String>() {{
            add(driver.findElement(By.id("result_0_name")).getText());
            add(driver.findElement(By.xpath("//*[contains(text(),'Lifestealer')]//span/..")).getText());
            add(driver.findElement(By.xpath("//*[contains(text(),'Immortal')]//span/..")).getText());
        }};
        firstItemName = driver.findElement(By.id("result_0_name"));
        return firstItemName;
    }

    void getInfoAboutItem() {
        waitTo(5, "//div[contains(text(),'Lifestealer')]");
        String tempHero = driver.findElement(By.xpath("//div[contains(text(),'Lifestealer')]")).getText();
        String[] temp = tempHero.split(" ");
        String tempRarity = driver.findElement(By.id("largeiteminfo_item_type")).getText();
        String[] tempRarityMass = tempRarity.split(" ");
        secondItemHero = temp[1];
        getSecondItemRarity = tempRarityMass[1];
        secondItemName = driver.findElement(By.id("largeiteminfo_item_name")).getText();
        filterItemAfter = new ArrayList<String>(){{
            add(secondItemName);
            add(secondItemHero);
            add(getSecondItemRarity);
        }};
    }







}
