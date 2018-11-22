import mfti.data.BrowsersData;
import mfti.drivers.WebDriverFactory;
import mfti.pages.MailPetsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MailPets_Test {
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
    public void check_show_more_button(){
        new MailPetsPage(driver)
            .open()
            .pageShouldBeOpened()
            .assertUrl()
            .countNewsBeforePressShowMore()
            .pressShowMoreButton()
            .countNewsAfterPressShowMore()
            .checkEqualNewsAfterPressShowMore()
            .checkButton();

    }
}
