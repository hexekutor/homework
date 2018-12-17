import mfti.calendar.Buttons;
import mfti.data.BrowsersData;
import mfti.drivers.WebDriverFactory;
import mfti.pages.WeatherCalendarPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class WeatherCalendar_Test {
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
    public void check_weather_calendar(){
        new WeatherCalendarPage(driver)
                .open()
                .pressCalendarButton()
                .pressButton(Buttons.PREVIOUS)
                .checkChangingMonth(Buttons.PREVIOUS)
                .pressButton(Buttons.NEXT)
                .checkChangingMonth(Buttons.NEXT)
                .pressDayButton(1)
                .checkDayButton();

    }
}
