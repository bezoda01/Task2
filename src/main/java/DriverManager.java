import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

class DriverManager {

    public static void setDriver(WebDriver driver) {
        DriverManager.driver = driver;
    }



    private static WebDriver driver;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    private String browser;

    public WebDriver getDriver() {
        try {
            if (driver == null) { //если объект ещё не создан


                if (getBrowser().equals("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    options.addArguments("--lang=en-GB");
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

    public void driverQuit() {
        driver.quit();
        setDriver(null);
    }
}
