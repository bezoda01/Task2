import DataClasses.Filters;
import DataClasses.Game;
import DataClasses.Product;
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
    public Game gameFirst;
    public Game gameSecond;
    public Filters realFilters;
    public Filters expectedFilters = new Filters("Dota 2", "Lifestealer", "Immortal", "\"golden\"");
    public Product beforeProduct;
    public Product afterProduct;

    @BeforeMethod
    void setUp() throws IOException {
        driverManager = new DriverManager();
        //first run
        driverManager.setBrowser("chrome");
        driverManager.chromeOptions("incognito", "english");

        mainPage = new MainPage(driverManager.getDriver());
        aboutPage = new AboutPage(driverManager.getDriver());
        gamePage = new GamePage(driverManager.getDriver());
        topSellersPage = new TopSellersPage(driverManager.getDriver());
        tradingPage = new TradingPage(driverManager.getDriver());
        performIs = new PerformIs();
    }

    @Test(priority = 3)
    void comparePlayers() throws IOException {
        mainPage.goToPage(performIs.util().getString("url"));
        Assert.assertTrue(mainPage.isThisPage(), "Page was not open");
        mainPage.clickToAbout();
        Assert.assertTrue(aboutPage.getPlayers(), "The number of players is now more than online");
        aboutPage.clickToMarket();
        Assert.assertTrue(mainPage.isThatMarket(), "Market Page was not open");
    }

    @Test(priority = 2)
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
        gameFirst = topSellersPage.returnGameInfoFirst();
        topSellersPage.firstGame();
        Assert.assertEquals(gamePage.gameName(), performIs.util().getString("gameName") , "Current page was not open");
        gameSecond = gamePage.returnGameInfoSecond();
        Assert.assertEquals(gameFirst.getName(), gameSecond.getName(), "Game info is different");
        Assert.assertEquals(gameFirst.getGameReleased(), gameSecond.getGameReleased(), "Game info is different");
        Assert.assertEquals(gameFirst.getPrice(), gameSecond.getPrice(), "Game info is different");
    }

    @Test(priority = 1)
    void checkProductInformation() throws IOException {
        mainPage.goToPage(performIs.util().getString("url"));
        Assert.assertTrue(mainPage.isThisPage(), "Current page was not open");
        mainPage.moveToCommunity();
        mainPage.clickToComMarket();
        Assert.assertTrue(tradingPage.checkIsOpen(), "Current page was not open");
        tradingPage.openAdvancedOptions();
        Assert.assertTrue(tradingPage.checkAdvOpIsOpen(), "SEARCH COMMUNITY MARKET was not open");
        tradingPage.clickToGameChoice();
        tradingPage.choiceGame("dota2");
        tradingPage.clickToSelectHero();
        tradingPage.selectHero("Lifestealer");
        tradingPage.clickAndInput("golden");
        tradingPage.clickRarityAndSearch("Immortal");
        realFilters = tradingPage.setFilterArr();
        Assert.assertEquals(realFilters.getGame(),expectedFilters.getGame(), "Element was not select" );
        Assert.assertEquals(realFilters.getHero(),expectedFilters.getHero(), "Element was not select" );
        Assert.assertEquals(realFilters.getRarity(),expectedFilters.getRarity(), "Element was not select" );
        Assert.assertEquals(realFilters.getInput(),expectedFilters.getInput(), "Element was not select" );
        Assert.assertTrue(tradingPage.methodInfoFirstFive(), "Not all elements have 'Golden'");
        Assert.assertTrue(tradingPage.removeIcons(), "Filters was not change");
        beforeProduct = tradingPage.setProductFirst();
        tradingPage.clickToItem();
        afterProduct = tradingPage.setProductSecond();
        Assert.assertEquals(beforeProduct.getName(), afterProduct.getName(), "Filters was not change");
        Assert.assertEquals(beforeProduct.getHero(), afterProduct.getHero(), "Filters was not change");
        Assert.assertEquals(beforeProduct.getRarity(), afterProduct.getRarity(), "Filters was not change");

    }

    @AfterMethod
    void quit() {
        driverManager.driverQuit();
    }
}
