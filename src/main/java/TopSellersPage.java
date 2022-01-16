import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;

public class TopSellersPage extends Singletone {
    public ArrayList<String> infoAboutGameFirst;


    WebElement checkPage() {
        return getDriver().findElement(By.cssSelector(".pageheader.full"));
    }

    WebElement blockOs() {
        return getDriver().findElement(By.id("os"));
    }

    WebElement blockNumber() {
        return getDriver().findElement(By.id("category3"));

    }

    WebElement blockLabels() {
        return getDriver().findElement(By.id("TagFilter_Container"));
    }

    void checkBlockOsIsOpen() {
        WebElement typeOs = getDriver().findElement(By.xpath("//div[contains(text(),'Операционная')]"));

        if (!blockOs().isEnabled()) {
            typeOs.click();
            waitTo(5, "//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']");
            getDriver().findElement(By.xpath("//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']")).click();
        } else {
            waitTo(5, "//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']");
            getDriver().findElement(By.xpath("//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']")).click();
        }
    }

    void checkBlockCoopIsOpen() {
        WebElement numberOfPlayers = getDriver().findElement(By.xpath("//div[contains(text(),'Количество')]"));

        if (!blockNumber().isEnabled()) {
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", numberOfPlayers);
            waitTo(5, "//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxCoop = getDriver().findElement(By.xpath("//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']"));
            executor.executeScript("arguments[0].click();", checkBoxCoop);

        } else {
            waitTo(5, "//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxCoop = getDriver().findElement(By.xpath("//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']"));
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", checkBoxCoop);
        }
    }

    void checkBlockLabels() {
        WebElement genreLabels = getDriver().findElement(By.xpath("//div[contains(text(),'Метки')]"));

        if (!blockLabels().isEnabled()) {
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", genreLabels);
            waitTo(5, "//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxLabels = getDriver().findElement(By.xpath("//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']"));
            executor.executeScript("arguments[0].click();", checkBoxLabels);
        } else {
            waitTo(5, "//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxLabels = getDriver().findElement(By.xpath("//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']"));
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", checkBoxLabels);
            implicitWait(5);
        }
    }


    WebElement checkBoxLabels() {
        return getDriver().findElement(By.xpath("//span[contains(@class, 'checked') and @data-value = '19']"));
    }

    WebElement checkBoxOs() {
        return getDriver().findElement(By.xpath("//span[contains(@class, 'checked') and @data-value = 'linux']"));
    }

    WebElement checkBoxCoop() {
        return getDriver().findElement(By.xpath("//span[contains(@class, 'checked') and @data-value = '48']"));
    }



    WebElement firstGame() {

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        infoAboutGameFirst = new ArrayList<String>(){{
            add(getDriver().findElement(By.xpath("//a[1]//div//div//span[@class = 'title']")).getText());
            add(getDriver().findElement(By.xpath("//a[1]//div//div[contains(@class,'released')]")).getText());
            add(getDriver().findElement(By.xpath("//a[1]//div//div[contains(@class,'price')]//div[contains(@class,'price')]")).getText());
        }};
        return getDriver().findElement(By.xpath("//div[@id = 'search_resultsRows']//a[1]"));
    }


}
