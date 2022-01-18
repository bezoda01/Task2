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

    private By blockOs = By.id("os");

    private By blockNunber = By.id("category3");

    private By loading = By.id("search_result_container");

    private By unique = By.id("additional_search_options");

    private By blockLabels = By.id("TagFilter_Container");



    private By gamesOnDemand = By.xpath("//div[@class = 'search_results_count']");
    private By gamesNum = By.xpath("//div[@class = 'search_pagination_left']");


    boolean checkPage() {
        return findById(unique).isEnabled();
    }

    void checkBlockOsIsOpen() {
        WebElement typeOs = findByXpath("//div[contains(text(),'Операционная')]");

        if (!findById(blockOs).isEnabled()) {
            typeOs.click();
            waitTo(5, "//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']");
            findByXpath("//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']").click();
        } else {
            waitTo(5, "//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']");
            findByXpath("//span[@data-value = 'linux']//span[@class = 'tab_filter_control_checkbox']").click();
        }
    }

    void checkBlockCoopIsOpen() {
        WebElement numberOfPlayers = findByXpath("//div[contains(text(),'Количество')]");

        if (!findById(blockNunber).isEnabled()) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", numberOfPlayers);
            waitTo(5, "//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxCoop = findByXpath("//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']");
            executor.executeScript("arguments[0].click();", checkBoxCoop);

        } else {
            waitTo(5, "//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxCoop = findByXpath("//span[@data-value= '48']//span[@class = 'tab_filter_control_checkbox']");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", checkBoxCoop);
        }
    }

    void checkBlockLabels() {
        WebElement genreLabels = findByXpath("//div[contains(text(),'Метки')]");

        if (!findById(blockLabels).isEnabled()) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", genreLabels);
            waitTo(5, "//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxLabels = findByXpath("//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']");
            executor.executeScript("arguments[0].click();", checkBoxLabels);
        } else {
            waitTo(5, "//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']");
            WebElement checkBoxLabels = findByXpath("//span[@data-value= '19']//span[@class = 'tab_filter_control_checkbox']");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", checkBoxLabels);
        }
    }


    boolean checkBoxLabels() {
        return findByXpath("//span[contains(@class, 'checked') and @data-value = '19']").isEnabled();
    }

    boolean checkBoxOs() {
        return findByXpath("//span[contains(@class, 'checked') and @data-value = 'linux']").isEnabled();
    }

    boolean checkBoxCoop() {
        return findByXpath("//span[contains(@class, 'checked') and @data-value = '48']").isEnabled();
    }


    void setInfoAboutGameFirst() {
        infoAboutGameFirst = new ArrayList<String>(){{
            add(findByXpath("//a[1]//div//div//span[@class = 'title']").getText());
            add(findByXpath("//a[1]//div//div[contains(@class,'released')]").getText());
            add(findByXpath("//a[1]//div//div[contains(@class,'price')]//div[contains(@class,'price')]").getText());
        }};
    }
    void firstGame() {

        waitTo(5, "//div[@id = 'search_results_loading' and @style = 'display: none;']");

        setInfoAboutGameFirst();

        findByXpath("//div[@id = 'search_resultsRows']//a[1]").click();
    }


}
