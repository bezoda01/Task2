import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;


public class PerformIs {
    Singletone singletone = new Singletone();


    UtilPage util() throws JsonProcessingException, FileNotFoundException {
        UtilPage utilPage = new UtilPage();
        writeJSON(utilPage);
        return readJSON();
    }

    //for xpath
    public WebElement findByXpath(String xpath) {
        return singletone.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement findByXpath(By xpath) {
        return singletone.getDriver().findElement(xpath);
    }

    //for id
    public WebElement findById(By element) {
        return singletone.getDriver().findElement(element);
    }


    //for Xpath
    public void waitTo(int seconds, String xpath) {
        new WebDriverWait(singletone.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitTo(int seconds, By xpath) {
        new WebDriverWait(singletone.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    //for SccSelector
    public void waitTo(String cssSelector, int seconds) {
        new WebDriverWait(singletone.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    //for id
    public void waitTo(By idElement, int seconds) {
        new WebDriverWait(singletone.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(idElement));
    }

    //for implicit wait
    public void waitToVisibility(int seconds, By element) {
        new WebDriverWait(singletone.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitToInvisibility(int seconds, WebElement element) {
        new WebDriverWait(singletone.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.invisibilityOf(element));
    }

    int returnInInt(String str) {
        int result;
        String temp;
        String delimetr = "\n";
        String[] masStr1 = str.split(delimetr);


        temp = masStr1[1];
        String[] masStr2 = temp.split(",");
        temp = masStr2[0] + masStr2[1] + masStr2[2];
        result = Integer.parseInt(temp);
        return result;
    }

    boolean onlineComparison(String inGames, String online) {
        return returnInInt(inGames) < returnInInt(online);
    }

    void tearDown() {
        singletone.getDriver().quit();
    }

    String correctPrice(String element) {
        String price;
        String[] temp = element.split(" ", 2);
        price = temp[0];
        return price;
    }

    private void writeJSON(UtilPage util) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try (FileWriter writer = new FileWriter("/Users/zenapoznak/IdeaProjects/Task2/src/main/resources/test.json")) {
            writer.write(gson.toJson(util));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UtilPage readJSON() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader reader = new BufferedReader(new FileReader("/Users/zenapoznak/IdeaProjects/Task2/src/main/resources/test.json"));
        return gson.fromJson(reader, UtilPage.class);
    }


    static class UtilPage {

        public String getGameName() {
            return gameName;
        }

        private String gameName = "Total War: WARHAMMER III";

        public String getUrl() {
            return url;
        }

        private String url = "https://store.steampowered.com";

        UtilPage() {

        }
    }
}
