import data.BrowsersData;
import drivers.WebDriverFactory;
import pages.PetsArticlePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class PetsArticle_Test {
    private WebDriver driver = null;

    @Before
    public void init() {
        driver = WebDriverFactory.getWebDriverInstance(BrowsersData.Chrome);
    }

    @After
    public void killSession() {
        driver.close();
        driver.quit();
    }

    @Test
    public void check_weather_article(){
        new PetsArticlePage(driver)
                .open()
                .checkDate();
    }
}
