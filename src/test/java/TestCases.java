import com.fasterxml.jackson.core.JsonProcessingException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.Locale;

public class TestCases extends Singletone{
    private MainPage mainPage;
    private AboutPage aboutPage;
    private GamePage gamePage;
    private TopSellersPage topSellersPage;
    private TradingPage tradingPage;

    private PerformIs performIs;


    @BeforeTest
    void setUp() throws FileNotFoundException, JsonProcessingException {

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

        topSellersPage.firstGame();
        Assert.assertEquals(gamePage.gameName(), performIs.util().getGameName() , "Current page was not open");
        Assert.assertEquals(topSellersPage.infoAboutGameFirst, gamePage.setInfoAboutGameSecond(), "Game info is different");
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
        tradingPage.clickAndInput("golden");
        tradingPage.clickRarityAndSearch();
        tradingPage.setFilterArr();
        for(String filter : tradingPage.filter) {
            Assert.assertTrue(tradingPage.returnElement(filter), "Element was not select" );
        }
        Assert.assertTrue(tradingPage.methodInfoFirstFive(), "Not all elements have 'Golden'");
        tradingPage.removeIcons();
        Assert.assertNotEquals(tradingPage.numbersResultsBefore, tradingPage.numbersResultsAfter, "Filters was not change");
        tradingPage.clickToItem();
        tradingPage.getInfoAboutItem();
        Assert.assertEquals(tradingPage.filterItemBefore, tradingPage.filterItemAfter, "Filters was not change");
    }

    @AfterTest
    void quit() {
        performIs.tearDown();
    }
}
