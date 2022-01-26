import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class TestCases {
    private MainPage mainPage;
    private AboutPage aboutPage;
    private GamePage gamePage;
    private TopSellersPage topSellersPage;
    private TradingPage tradingPage;
    private PerformIs performIs;

    private DriverManager driverManager;


    @BeforeMethod
    void setUp(){
        driverManager = new DriverManager();
        //first run
        driverManager.setBrowser("chrome");

        mainPage = new MainPage(driverManager.getDriver());
        aboutPage = new AboutPage(driverManager.getDriver());
        gamePage = new GamePage(driverManager.getDriver());
        topSellersPage = new TopSellersPage(driverManager.getDriver());
        tradingPage = new TradingPage(driverManager.getDriver());
        performIs = new PerformIs();
    }

    @Test
    void comparePlayers() throws IOException {
        mainPage.goToPage(performIs.util().getString("url"));
        Assert.assertTrue(mainPage.isThisPage(), "Page was not open");
        mainPage.clickToAbout();
        Assert.assertTrue(aboutPage.getPlayers(), "The number of players is now more than online");
        aboutPage.clickToMarket();
        Assert.assertTrue(mainPage.isThatMarket(), "Market Page was not open");
    }

    @Test
    void checkGameInformation() throws IOException {
        mainPage.goToPage(performIs.util().getString("url"));
        Assert.assertTrue(mainPage.isThisPage(),"Current page was not open");
        mainPage.goCursorToNew();
        mainPage.clickToLeader();
        Assert.assertTrue(topSellersPage.checkPage(), "Current page was not open");
        topSellersPage.checkBlockOsIsOpen();
        Assert.assertTrue(topSellersPage.checkBoxOs(), "No search item selected");
        topSellersPage.checkBlockCoopIsOpen();
        Assert.assertTrue(topSellersPage.checkBoxCoop(), "No search item selected");
        topSellersPage.checkBlockLabels();
        Assert.assertTrue(topSellersPage.checkBoxLabels(), "No search item selected");
        Assert.assertTrue(topSellersPage.checkNumResult(), "number result is different");
        topSellersPage.firstGame();
        Assert.assertEquals(gamePage.gameName(), performIs.util().getString("gameName") , "Current page was not open");
        gamePage.setInfoAboutGameSecond();
        Assert.assertEquals(topSellersPage.infoAboutGameFirst, gamePage.infoAboutGameSecond, "Game info is different");
    }

    @Test
    void checkProductInformation() throws IOException {
        mainPage.goToPage(performIs.util().getString("url"));
        Assert.assertTrue(mainPage.isThisPage(), "Current page was not open");
        mainPage.moveToCommunity();
        mainPage.clickToComMarket();
        Assert.assertTrue(tradingPage.checkIsOpen(), "Current page was not open");
        tradingPage.openAdvancedOptions();
        Assert.assertTrue(tradingPage.checkAdvOpIsOpen(), "SEARCH COMMUNITY MARKET was not open");
        tradingPage.clickToGameChoice();
        tradingPage.choiceGame();
        tradingPage.clickToSelectHero();
        tradingPage.selectLifeStealer();
        tradingPage.clickAndInput(performIs.util().getString("golden"));
        tradingPage.clickRarityAndSearch();
        tradingPage.setFilterArr();
        for(String filter : tradingPage.filter) {
            Assert.assertTrue(tradingPage.returnElement(filter), "Element was not select" );
        }
        Assert.assertTrue(tradingPage.methodInfoFirstFive(), "Not all elements have 'Golden'");
        Assert.assertTrue(tradingPage.removeIcons(), "Filters was not change");
        tradingPage.clickToItem();
        tradingPage.getInfoAboutItem();
        Assert.assertEquals(tradingPage.filterItemBefore, tradingPage.filterItemAfter, "Filters was not change");
    }

    @AfterMethod
    void quit() {
        driverManager.driverQuit();
    }
}
