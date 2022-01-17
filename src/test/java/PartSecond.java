import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PartSecond extends Singletone {
    private MainPage mainPage;
    private TopSellersPage topSellersPage;
    private GamePage gamePage;
    private PerformIs performIs;


    @BeforeTest
    void setUp() {
        getDriver("chrome");

        mainPage = new MainPage(getDriver());
        topSellersPage = new TopSellersPage(getDriver());
        gamePage = new GamePage(getDriver());
        performIs = new PerformIs();
    }

    @Test
    void test() {
        mainPage.goToPage();
        Assert.assertEquals(mainPage.clickToAbout().getText(), "О STEAM", "Current page was not open");
        mainPage.goCursorToNew();
        mainPage.clickToLeader();
        Assert.assertTrue(topSellersPage.checkPage().isEnabled(), "Current page was not open");
        topSellersPage.checkBlockOsIsOpen();
        Assert.assertTrue(topSellersPage.checkBoxOs().isEnabled(), "No search item selected");
        topSellersPage.checkBlockCoopIsOpen();
        Assert.assertTrue(topSellersPage.checkBoxCoop().isEnabled(), "No search item selected");
        topSellersPage.checkBlockLabels();
        Assert.assertTrue(topSellersPage.checkBoxLabels().isEnabled(), "No search item selected");
        topSellersPage.firstGame().click();
        Assert.assertEquals(gamePage.gameName().getText(), "Total War: WARHAMMER III" , "Current page was not open");
        Assert.assertEquals(topSellersPage.infoAboutGameFirst, gamePage.setInfoAboutGameSecond(), "Game info is different");
    }

    @AfterTest
    void quit() {
        performIs.tearDown();
    }

}
