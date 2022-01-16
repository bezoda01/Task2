import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class Singletone {

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
            // exception to log for instantiation problem
        }
        return driver;
    }

    //for Xpath
    public void waitTo(int seconds, String xpath) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    //for SccSelector
    public void waitTo(String cssSelector, int seconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    //for implicit wait
    public void implicitWait(int seconds) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));

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
        getDriver().quit();
    }

    String correctPrice(String element) {
        String price;
        String[] temp = element.split(" ",2);
        price = temp[0];
        return price;
    }
}
