import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PartFirst extends Singletone{
    private MainPage mainPage;
    private AboutPage aboutPage;
    private PerformIs performIs;

    @BeforeTest
    void setUp() {
        //first run
        getDriver("chrome");

        mainPage = new MainPage(getDriver());
        aboutPage = new AboutPage(getDriver());
        performIs = new PerformIs();


    }

    @Test
    void test() {
        mainPage.goToPage();
        Assert.assertTrue(mainPage.clickToAbout().isEnabled(), "Page was not open");
        mainPage.clickToAbout().click();
        Assert.assertTrue(aboutPage.onlineComparison(), "The number of players is now more than online");
        aboutPage.clickToMarket().click();
        Assert.assertEquals(mainPage.isThatMarket().getText(),"Ваш магазин", "Market Page was not open");
    }

    @AfterTest
    void quit() {
        performIs.tearDown();
    }
}
