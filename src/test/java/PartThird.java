import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PartThird extends Singletone{
    private MainPage mainPage;
    private TradingPage tradingPage;

    @BeforeTest
    void setUp() {
        getDriver("chrome");

        mainPage = new MainPage();
        tradingPage = new TradingPage();
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
        tradingPage.setFilterArr();
        for(String filter : tradingPage.filter) {
            Assert.assertTrue(tradingPage.returnElement(filter).isEnabled(), "Element was not select" );
        }
        Assert.assertTrue(tradingPage.methodInfoFirstFive(), "Not all elements have 'Golden'");
        tradingPage.removeIcons();
        Assert.assertNotEquals(tradingPage.numbersResultsBefore,tradingPage.numbersResultsAfter, "Filters was not change");
        tradingPage.clickToItem().click();
        tradingPage.getInfoAboutItem();
        Assert.assertEquals(tradingPage.filterItemBefore, tradingPage.filterItemAfter, "Filters was not change");
    }



    @AfterTest
    void quit() {
        tearDown();
    }
}
