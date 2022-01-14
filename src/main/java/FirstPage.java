import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstPage {

private static WebDriver driver;

FirstPage(WebDriver driver) {
    FirstPage.driver = driver;
}

public WebDriver getDriver() {
    if(driver == null){ //если объект ещё не создан
        driver = new ChromeDriver();//создать новый объект
    }
    return driver;//вернуть объект
}



}
