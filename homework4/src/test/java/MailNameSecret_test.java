import mfti.data.BrowsersData;
import mfti.drivers.WebDriverFactory;
import mfti.pages.MailNameSecretPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MailNameSecret_test {
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
        new MailNameSecretPage(driver)
                .open("namesecret", "namesecret")
                .pageShouldBeOpened()
                .pressLetterButton()
                .checkLetterButton();

    }
    @Test
    public void check_input_name(){
        new MailNameSecretPage(driver)
                .open("namesecret", "namesecret")
                .pageShouldBeOpened()
                .typeName("максим")
                .checkSuggestMenu()
                .pressSuggestElement()
                .assertSearchButton();
    }
    @Test
    public void check_select(){
        new MailNameSecretPage(driver)
                .open("namesecret", "namesecret")
                .pageShouldBeOpened()
                .typeName("максим")
                .pressSelectElement()
                .assertSelect();
    }
}
