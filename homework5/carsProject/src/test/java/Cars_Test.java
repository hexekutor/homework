import mfti.data.BrowsersData;
import mfti.drivers.WebDriverFactory;
import mfti.pages.CarsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Cars_Test {
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
    public void check_letter_button(){
        String parameter = "Управляемость";
        new CarsPage(driver)
                .open()
                .pressParameter(parameter)
                .checkPopup(parameter)
                .closePopup();
    }
}
