import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PartFirst extends Singletone{
    private MainPage mainPage;
    private AboutPage aboutPage;


    @BeforeTest
    void setUp() {
        mainPage = new MainPage();
        aboutPage = new AboutPage();

        createInstance("chrome");
    }

    @Test
    void test() {
        mainPage.goToPage();
        Assert.assertEquals(mainPage.clickToAbout().getText(), "О STEAM", "Page was not open");
        mainPage.clickToAbout().click();
        Assert.assertTrue(aboutPage.onlineComparison(), "The number of players is now more than online");
        aboutPage.clickToMarket().click();
        Assert.assertEquals(mainPage.isThatMarket().getText(),"Ваш магазин", "Market Page was not open");
    }

    @AfterTest
    void quit() {
        tearDown();
    }


}
