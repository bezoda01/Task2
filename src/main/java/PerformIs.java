import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;


public class PerformIs {

    public ArrayList<String> getInfoAboutGameSecond() {
        return infoAboutGameSecond;
    }

    public ArrayList<String> infoAboutGameSecond;

    void setInfoAboutGameSecond(String name, String released, String price) {
        infoAboutGameSecond = new ArrayList<String>() {{
            add(name);
            add(released);
            add(price);
        }};
    }
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


    int parsToIntFirst(String text) {
        int result;
        String[] temp1 = text.split(" ");
        String tempstr1 = temp1[4];
        String[] temp2 = tempstr1.split("\\.");
        String tempstr2 = temp2[0];

        return result = Integer.parseInt(tempstr2);
    }

    String returnString(String hero) {
        String[] temp = hero.split(" ");
        return temp[1];
    }

    String returnString2(String element) {
        String[] tempRarityMass = element.split(" ");
        return tempRarityMass[1];
    }

    boolean infoFirstFiveGolden( ArrayList<String> list) {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            String temp = singletone.getDriver().findElement(By.id("result_" + i + "_name")).getText();
            String[] tempMass = temp.split(" ");
            Collections.addAll(list, tempMass);
        }
        for (String s : list) {
            if (s.equals("Golden")) {
                counter++;
            }
        }
        return counter == 5;
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




    static class UtilPage {

        public String getGolden() {
            return golden;
        }

        private String golden = "golden";


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
