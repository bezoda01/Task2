import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

import java.util.Collections;

public class TradingPage extends Singletone {
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
        return getDriver().findElement(By.xpath("//span[contains(text(),'Активные')]"));
    }

    WebElement openAdvancedOptions() {
        return getDriver().findElement(By.xpath("//span[contains(text(),'Дополнительные')]"));
    }

    WebElement checkAdvOpIsOpen() {
        return getDriver().findElement(By.xpath("//div[contains(text(),'Поиск')]"));
    }

    WebElement clickToGameChoice() {
        return getDriver().findElement(By.id("app_option_0_selected"));
    }

    WebElement choiceGame() {
        return getDriver().findElement(By.id("app_option_570"));
    }

    void clickToSelectHero() {
        waitTo(5, "//select[@name='category_570_Hero[]']");
        WebElement selectHero = getDriver().findElement(By.xpath("//select[@name='category_570_Hero[]']"));
        selectHero.click();
    }

    void selectLifeStealer() {
        waitTo(5, "//option[@value = 'tag_npc_dota_hero_life_stealer']");
        WebElement lifeStealer = getDriver().findElement(By.xpath("//option[@value = 'tag_npc_dota_hero_life_stealer']"));
        lifeStealer.click();
    }

    void clickAndInput() {
        WebElement search = getDriver().findElement(By.id("advancedSearchBox"));
        Actions actions = new Actions(getDriver());
        actions.doubleClick(search).sendKeys("golden").release().build().perform();
    }

    void clickRarityAndSearch() {
        WebElement immortal = getDriver().findElement(By.id("tag_570_Rarity_Rarity_Immortal"));
        Actions actions = new Actions(getDriver());
        actions.click(immortal).release().build().perform();
        clickSearch().click();
    }

    WebElement clickSearch() {
        return getDriver().findElement(By.xpath("//div[@onclick]//span[contains(text(),'Поиск')]"));
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
            String temp = getDriver().findElement(By.id("result_" + i + "_name")).getText();
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
        return getDriver().findElement(By.xpath(element));
    }

    void removeIcons() {
        numbersResultsBefore = Integer.parseInt(getDriver().findElement(By.id("searchResults_total")).getText());
        WebElement goldenRemove = getDriver().findElement(By.xpath("//*[contains(text(),'golden')]//span"));
        goldenRemove.click();
        WebElement dotaRemove = getDriver().findElement(By.xpath("//a[@href][1]/span"));
        dotaRemove.click();
        numbersResultsAfter = Integer.parseInt(getDriver().findElement(By.id("searchResults_total")).getText());
    }

    WebElement clickToItem() {
        filterItemBefore = new ArrayList<String>() {{
            add(getDriver().findElement(By.id("result_0_name")).getText());
            add(getDriver().findElement(By.xpath("//*[contains(text(),'Lifestealer')]//span/..")).getText());
            add(getDriver().findElement(By.xpath("//*[contains(text(),'Immortal')]//span/..")).getText());
        }};
        firstItemName = getDriver().findElement(By.id("result_0_name"));
        return firstItemName;
    }

    void getInfoAboutItem() {
        waitTo(5, "//div[contains(text(),'Lifestealer')]");
        String tempHero = getDriver().findElement(By.xpath("//div[contains(text(),'Lifestealer')]")).getText();
        String[] temp = tempHero.split(" ");
        String tempRarity = getDriver().findElement(By.id("largeiteminfo_item_type")).getText();
        String[] tempRarityMass = tempRarity.split(" ");
        secondItemHero = temp[1];
        getSecondItemRarity = tempRarityMass[1];
        secondItemName = getDriver().findElement(By.id("largeiteminfo_item_name")).getText();
        filterItemAfter = new ArrayList<String>(){{
            add(secondItemName);
            add(secondItemHero);
            add(getSecondItemRarity);
        }};
    }







}
