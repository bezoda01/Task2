import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class DriverManager {

    public static void setDriver(WebDriver driver) {
        DriverManager.driver = driver;
    }

    private static WebDriver driver;

    public static ChromeOptions getOptions() {
        return options;
    }

    public static void setOptions(ChromeOptions options) {
        DriverManager.options = options;
    }

    private static ChromeOptions options;

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
                    WebDriverManager.chromedriver().setup();
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments(PerformIs.util().getString("incognito"));
//                    options.addArguments("--start-maximized");
                    driver = new ChromeDriver(getOptions());
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

    public void chromeOptions(String incognito, String language) throws IOException {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", PerformIs.utilConfig().getString(language));
        options.addArguments(PerformIs.utilConfig().getString(incognito));
//        options.addArguments(PerformIs.util().getString(incognito), PerformIs.util().getString(language));
        options.setExperimentalOption("prefs", prefs);
        options.setCapability(ChromeOptions.CAPABILITY, options);
        setOptions(options);
    }

}
