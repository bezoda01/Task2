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

    private final int SECONDS = 5;


    static JSONObject utilData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("testData.json"))){
            String file;
            StringBuilder temp = new StringBuilder();
            while((file = reader.readLine()) != null) {
                temp.append(file);
            }
            return new JSONObject(temp.toString());
        }
    }

    static JSONObject utilConfig() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("testConfig.json"))){
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
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(SECONDS)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return driverManager.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement findByXpath(By xpath) {
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(SECONDS)).until(ExpectedConditions.presenceOfElementLocated(xpath));
        return driverManager.getDriver().findElement(xpath);
    }

    //for id
    public WebElement findById(By element) {
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(SECONDS)).until(ExpectedConditions.presenceOfElementLocated(element));
        return driverManager.getDriver().findElement(element);
    }

    public void waitTo(By locator) {
        new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(SECONDS)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }



    String correctPrice(String element) {
        String price;
        String[] temp = element.split(" ");
        price = temp[0];
        return price;
    }


    boolean parsToIntFirst(String text, String intSize) {

        boolean answer = false;

        String[] temp1 = text.split("\\.");

        String tempstr1 = temp1[0];

        String[] temp2 = tempstr1.split(" ");
        for (String s : temp2) {
            if (s.equals(intSize)) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    String returnString(String hero) {
        String[] temp = hero.split(" ");
        return temp[2];
    }

    //Used By: Lifestealer
    //Используется: Lifestealer
    String returnString2(String element) {
        String[] tempRarityMass = element.split(" ");
        return tempRarityMass[0];
    }

    boolean infoFirstFiveGolden() {
        ArrayList<String> list = new ArrayList<>();
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
