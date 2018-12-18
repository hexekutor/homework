package wait;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandartWaiter {

    private WebDriver driver = null;

    public StandartWaiter(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitForCondition(ExpectedCondition condition) {
        WebDriverWait webDriverWait = new WebDriverWait(driver,
                Integer.parseInt(System.getProperty("webdriver.timeouts.implicitlywait"))/1000);

        try {
           webDriverWait.until(condition);
           return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }
}
