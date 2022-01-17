import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PerformIs {
    Singletone singletone = new Singletone();


    //for Xpath
    public void waitTo(int seconds, String xpath) {
        new WebDriverWait(singletone.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    //for SccSelector
    public void waitTo(String cssSelector, int seconds) {
        new WebDriverWait(singletone.getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
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


}
