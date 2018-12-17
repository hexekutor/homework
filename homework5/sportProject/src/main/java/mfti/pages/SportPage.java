package mfti.pages;


import mfti.navigation.Domain;
import mfti.navigation.Url;
import mfti.navigation.UrlParam;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;

@Domain("https://sportmail.ru")
@UrlParam(
        {
                @Url(name = "article", url = "/news/football-worldcup//%1/")
        }
)
public class SportPage extends Page<SportPage>{

    @FindBy(css = "[class = 'error-page__title']")
    private WebElement errorTitle;

    public SportPage(WebDriver driver) {
        super(driver);
    }

    public SportPage open(String ... args) {
        super.open(args);
        return this;
    }
    private static boolean isElementExists(WebElement element){
        try
        {
            element.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }
    public SportPage checkPageOpenedWithoutError(){
        assertFalse("Страница открылась без ошибок", isElementExists(errorTitle));
        return this;
    }
    public SportPage getLogs(){
        List<LogEntry> entries = driver.manage().logs().get(LogType.BROWSER).getAll();
        System.out.println(entries.size() + " " + LogType.BROWSER + " log entries found");
        for (LogEntry entry : entries) {
            System.out.println(
                    new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        return this;
    }
}
