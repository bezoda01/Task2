import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PartOne{
    private MarketPage marketPage;
    private AboutPage aboutPage;


    @BeforeTest
    void setUp() {
        marketPage = new MarketPage();
        aboutPage = new AboutPage();
        marketPage.createInstance("chrome");
    }

    @Test
    void test() {
        marketPage.goToPage();
        Assert.assertEquals(marketPage.clickToAbout().getText(), "О STEAM", "Page was not open");
        marketPage.clickToAbout().click();
        Assert.assertTrue(aboutPage.onlineComparison(), "The number of players is now more than online");
        aboutPage.clickToMarket().click();
        Assert.assertEquals(marketPage.isThatMarket().getText(),"Ваш магазин", "Market Page was not open");
    }

    @AfterTest
    void quit() {
        marketPage.quit();
        aboutPage.quit();
    }


}
