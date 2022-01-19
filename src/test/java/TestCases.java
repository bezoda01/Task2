import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

public class TestCases extends Singletone{
    private MainPage mainPage;
    private AboutPage aboutPage;
    private GamePage gamePage;
    private TopSellersPage topSellersPage;
    private TradingPage tradingPage;

    private PerformIs performIs;


    @BeforeMethod
    void setUp(){

        //first run
        getDriver("chrome");

        mainPage = new MainPage(getDriver());
        aboutPage = new AboutPage(getDriver());
        gamePage = new GamePage(getDriver());
        topSellersPage = new TopSellersPage(getDriver());
        tradingPage = new TradingPage(getDriver());
        performIs = new PerformIs();
    }

    @Test
    void testCase1() throws FileNotFoundException, JsonProcessingException {
        mainPage.goToPage(performIs.util().getUrl());
        Assert.assertTrue(mainPage.isThisPage(), "Page was not open");
        mainPage.clickToAbout();
        Assert.assertTrue(aboutPage.getPlayers(), "The number of players is now more than online");
        aboutPage.clickToMarket();
        Assert.assertTrue(mainPage.isThatMarket(), "Market Page was not open");
    }

    @Test
    void testCase2() throws FileNotFoundException, JsonProcessingException {
        mainPage.goToPage(performIs.util().getUrl());
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
        Assert.assertEquals(gamePage.gameName(), performIs.util().getGameName() , "Current page was not open");
        gamePage.setInfoAboutGameSecond();
        Assert.assertEquals(topSellersPage.infoAboutGameFirst, gamePage.infoAboutGameSecond, "Game info is different");
    }

    @Test
    void testCase3() throws FileNotFoundException, JsonProcessingException {
        mainPage.goToPage(performIs.util().getUrl());
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
        tradingPage.clickAndInput(performIs.util().getGolden());
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
        performIs.tearDown();
        setDriver(null);
    }
}
