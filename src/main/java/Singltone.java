import com.sun.tools.hat.internal.model.Root;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class Singleton {

    private static WebDriver driver;

    public void setDriverClass(Class<?> driverClass) {
        this.driverClass = driverClass;
    }

    private Class<?> driverClass;

    public WebDriver getDriver() {

        if (driver == null) { //если объект ещё не создан
            try {
                driver = (WebDriver) driverClass.newInstance();//создать новый объект
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return driver;//вернуть объект
    }



    public WebDriver createInstance(String browser) {

        try {

            DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());
            Class<?> driverClass = Class.forName(driverManagerType.browserClass());

            setDriverClass(Class.forName(driverManagerType.browserClass()));

            WebDriverManager.getInstance(driverManagerType).setup();
            driver = (WebDriver) driverClass.newInstance();
            driver.manage().window().maximize();


        } catch (IllegalAccessException | ClassNotFoundException e) {
            // exception or log for class not found
        } catch (InstantiationException e) {
            // exception of log for instantiation problem
        }
        return driver;
    }

    public void waitTo(int seconds, String xpath) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
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
}
