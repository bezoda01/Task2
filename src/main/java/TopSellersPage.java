import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class TopSellersPage extends PerformIs {
    private final WebDriver driver;

    TopSellersPage(WebDriver driver) {
        this.driver = driver;
    }


    public ArrayList<String> infoAboutGameFirst;


    WebElement checkPage() {
        return driver.findElement(By.cssSelector(".pageheader.full"));
    }

    WebElement blockOs() {
        return driver.findElement(By.id("os"));
    }

    WebElement blockNumber() {
        return driver.findElement(By.id("category3"));

    }

    WebElement blockLabels() {
        return driver.findElement(By.id("TagFilter_Container"));
    }

    void checkBlockOsIsOpen() {
        WebElement typeOs = driver.findElement(By.xpath("//div[contains(text(),'Операционная')]"));

        if (!blockOs().isEnabled()) {
            typeOs.click();
            waitTo(5, "//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']");
            driver.findElement(By.xpath("//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']")).click();
        } else {
            waitTo(5, "//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']");
            driver.findElement(By.xpath("//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']")).click();
        }
    }

    void checkBlockCoopIsOpen() {
        WebElement numberOfPlayers = driver.findElement(By.xpath("//div[contains(text(),'Количество')]"));

        if (!blockNumber().isEnabled()) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", numberOfPlayers);
            waitTo(5, "//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxCoop = driver.findElement(By.xpath("//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']"));
            executor.executeScript("arguments[0].click();", checkBoxCoop);

        } else {
            waitTo(5, "//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxCoop = driver.findElement(By.xpath("//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", checkBoxCoop);
        }
    }

    void checkBlockLabels() {
        WebElement genreLabels = driver.findElement(By.xpath("//div[contains(text(),'Метки')]"));

        if (!blockLabels().isEnabled()) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", genreLabels);
            waitTo(5, "//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxLabels = driver.findElement(By.xpath("//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']"));
            executor.executeScript("arguments[0].click();", checkBoxLabels);
        } else {
            waitTo(5, "//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxLabels = driver.findElement(By.xpath("//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", checkBoxLabels);
            implicitWait(5);
        }
    }


    WebElement checkBoxLabels() {
        return driver.findElement(By.xpath("//span[contains(@class, 'checked') and @data-value = '19']"));
    }

    WebElement checkBoxOs() {
        return driver.findElement(By.xpath("//span[contains(@class, 'checked') and @data-value = 'linux']"));
    }

    WebElement checkBoxCoop() {
        return driver.findElement(By.xpath("//span[contains(@class, 'checked') and @data-value = '48']"));
    }


    void setInfoAboutGameFirst() {
        infoAboutGameFirst = new ArrayList<String>(){{
            add(driver.findElement(By.xpath("//a[1]//div//div//span[@class = 'title']")).getText());
            add(driver.findElement(By.xpath("//a[1]//div//div[contains(@class,'released')]")).getText());
            add(driver.findElement(By.xpath("//a[1]//div//div[contains(@class,'price')]//div[contains(@class,'price')]")).getText());
        }};
    }
    WebElement firstGame() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setInfoAboutGameFirst();

        return driver.findElement(By.xpath("//div[@id = 'search_resultsRows']//a[1]"));
    }


}
