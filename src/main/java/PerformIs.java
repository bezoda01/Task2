import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.By;
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


    //for Xpath
    public void waitTo(int seconds, String xpath) {
        new WebDriverWait(singletone.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
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
    public void implicitWait(int seconds) {
        singletone.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));

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

    void tearDown() {
        singletone.getDriver().quit();
    }

    String correctPrice(String element) {
        String price;
        String[] temp = element.split(" ", 2);
        price = temp[0];
        return price;
    }

    private void writeJSON(UtilPage util){
        GsonBuilder builder= new GsonBuilder();
        Gson gson = builder.create();
        try (FileWriter writer = new FileWriter("/Users/zenapoznak/IdeaProjects/Task2/src/main/resources/test.json")){
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
        public String getUrl() {
            return url;
        }
        private String url = "https://store.steampowered.com";




        UtilPage() {

        }



    }
}
