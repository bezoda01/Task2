import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PartOne{
    private WebDriver driver;
    private FirstPage firstPage;

    @BeforeTest
    void setUp() {
        WebDriverManager.chromedriver().setup();
        firstPage = new FirstPage(driver);
    }

    @Test
    void test() {
        firstPage.goToPage();
        Assert.assertEquals(firstPage.clickToAbout().getText(), "О STEAM", "Page was not open");
        firstPage.clickToAbout().click();
        Assert.assertTrue(firstPage.onlineComparison(), "The number of players is now more than online");
        firstPage.clickToMarket().click();
        Assert.assertEquals(firstPage.isThatMarket().getText(),"Ваш магазин");
    }

    @AfterTest
    void quit() {
        firstPage.quit();
    }


}
