import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TradingPage extends Singletone {
    ArrayList<String> firstFive = new ArrayList<>();


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

    WebElement filterCheck() {
        return getDriver().findElement(By.xpath("//div[@style = 'display: inline-block; margin: 10px 0;']//a[contains(text(),'golden') or contains(text(),'Immortal') or contains(text(),'hero') or contains(text(),'category_570_Hero')]"));
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


}
