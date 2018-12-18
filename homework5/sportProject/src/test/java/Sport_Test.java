import mfti.data.BrowsersData;
import mfti.drivers.WebDriverFactory;
import mfti.pages.SportPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Sport_Test {
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
    public void check_sport_page(){
        new SportPage(driver)
                .open("article", "32246552")
                .checkPageOpenedWithoutError()
                .getLogs();
    }

    /*@Test
    public void check_sport_news_page(){
        new SportArticlePage(driver)
                .open()
                .checkPageOpenedWithoutError();
    }*/
}
