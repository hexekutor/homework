package mfti.pages;

import mfti.navigation.DefaultUrl;
import mfti.navigation.Domain;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertFalse;

@Domain("https://sportmail.ru")
@DefaultUrl("/")
public class SportArticlePage extends Page<SportArticlePage>{

    @FindBy(css = "[class = 'error-page__title']")
    private WebElement errorTitle;

    String errorTitleSelector = errorTitle.toString();

    public SportArticlePage(WebDriver driver) {
        super(driver);
    }

    public SportArticlePage open() {
        super.open();
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
    public SportArticlePage checkPageOpenedWithoutError(){
        assertFalse("Страница открылась без ошибок", isElementExists(errorTitle));
        return this;
    }
}
