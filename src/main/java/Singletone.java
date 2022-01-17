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
                } else {
                    setBrowser(browser);
                    DriverManagerType driverManagerType = DriverManagerType.valueOf(getBrowser().toUpperCase());
                    Class<?> driverClass = Class.forName(driverManagerType.browserClass());
                    WebDriverManager.getInstance(driverManagerType).setup();
                    driver = (WebDriver) driverClass.newInstance();
                    driver.manage().window().maximize();
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

}
