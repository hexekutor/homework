import mfti.data.BrowsersData;
import mfti.drivers.WebDriverFactory;
import mfti.pages.GooglePageMainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class CheckResultsOfSearch_Test {
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
    public void check_results_of_search_Hello_world(){
        new GooglePageMainPage(driver)
             .open()
             .pageShouldBeOpened()
             .typeSearchText("Hello World")
             .pressSearchButton()
             .resultBarShouldExist();


    }

}
