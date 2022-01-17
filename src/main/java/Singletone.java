import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class Singletone {

    private static WebDriver driver;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    private String browser;

    public WebDriver getDriver(String browser) {
        setBrowser(browser);
        try {
            if (driver == null) { //если объект ещё не создан


                if (getBrowser().equals("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    return driver;
                } else {
                    setBrowser(browser);
                    DriverManagerType driverManagerType = DriverManagerType.valueOf(getBrowser().toUpperCase());
                    Class<?> driverClass = Class.forName(driverManagerType.browserClass());
                    WebDriverManager.getInstance(driverManagerType).setup();
                    driver = (WebDriver) driverClass.newInstance();
                    driver.manage().window().maximize();
                    return driver;
                }
            }

            } catch(InstantiationException | IllegalAccessException | ClassNotFoundException e){
                e.printStackTrace();
            }
        return driver;
        }



    public WebDriver getDriver() {
        if(driver == null) {
            driver = getDriver(getBrowser());
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
        String[] temp = element.split(" ", 2);
        price = temp[0];
        return price;
    }
}
