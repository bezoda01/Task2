import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PartThird extends Singletone{
    private MainPage mainPage;
    private TradingPage tradingPage;

    @BeforeTest
    void setUp() {
        mainPage = new MainPage();
        tradingPage = new TradingPage();
        createInstance("chrome");
    }

    @Test
    void test() {
        mainPage.goToPage();
        Assert.assertTrue(mainPage.clickToAbout().isEnabled(), "Current page was not open");
        mainPage.moveToCommunity();
        mainPage.clickToComMarket();
        Assert.assertTrue(tradingPage.checkIsOpen().isEnabled(), "Current page was not open");
        tradingPage.openAdvancedOptions().click();
        Assert.assertTrue(tradingPage.checkAdvOpIsOpen().isEnabled(), "SEARCH COMMUNITY MARKET was not open");
        tradingPage.clickToGameChoice().click();
        tradingPage.choiceGame().click();
        tradingPage.clickToSelectHero();
        tradingPage.selectLifeStealer();
        tradingPage.clickAndInput();
        tradingPage.clickRarityAndSearch();
        Assert.assertTrue(tradingPage.filterCheck().isEnabled(), "Filters was not select");
        Assert.assertTrue(tradingPage.methodInfoFirstFive(), "Not all elements have 'Golden'");
    }



//    @AfterTest
//    void quit() {
//        tearDown();
//    }
}
