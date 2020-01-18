import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;
    String BASE_URL = "https://www.autohero.com/de";
    MainPage mainPage;
    SearchPage searchPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\QA\\sources\\selenium_basic\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = PageFactory.initElements(driver, MainPage.class);
        searchPage = PageFactory.initElements(driver, SearchPage.class);

        driver.manage().window().maximize();
        driver.navigate().to(BASE_URL);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
