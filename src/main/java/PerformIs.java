import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;


public class PerformIs {
    DriverManager driverManager = new DriverManager();


    JSONObject util() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("testData.json"))){
            String file;
            StringBuilder temp = new StringBuilder();
            while((file = reader.readLine()) != null) {
                temp.append(file);
            }
            return new JSONObject(temp.toString());
        }
    }


    //for xpath
    public WebElement findByXpath(String xpath) {
        return driverManager.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement findByXpath(By xpath) {
        return driverManager.getDriver().findElement(xpath);
    }

    //for id
    public WebElement findById(By element) {
        return driverManager.getDriver().findElement(element);
    }


    //for Xpath
    public void waitTo(int seconds, String xpath) {
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitTo(int seconds, By xpath) {
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    //for SccSelector
    public void waitTo(String cssSelector, int seconds) {
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    //for id
    public void waitTo(By idElement, int seconds) {
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(idElement));
    }

    //for implicit wait
    public void waitToVisibility(int seconds, By element) {
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitToInvisibility(int seconds, WebElement element) {
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.invisibilityOf(element));
    }

    String correctPrice(String element) {
        String price;
        String[] temp = element.split(" ");
        price = temp[0];
        return price;
    }


    int parsToIntFirst(String text) {
        
        String[] temp1 = text.split(" ");
        String tempstr1 = temp1[4];
        String[] temp2 = tempstr1.split("\\.");
        String tempstr2 = temp2[0];

        return Integer.parseInt(tempstr2);
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
            String temp = driverManager.getDriver().findElement(By.id("result_" + i + "_name")).getText();
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

}
