import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class PartOne{
    private WebDriver driver;
    private FirstPage firstPage;

    @BeforeTest
    void setUp() {
        firstPage = new FirstPage(driver);
    }




}
