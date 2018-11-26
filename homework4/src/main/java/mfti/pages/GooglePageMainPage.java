package mfti.pages;

import mfti.navigation.DefaultUrl;
import mfti.navigation.Domain;
import mfti.navigation.UrlPattern;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;
@Domain("https://www.google.com")
@DefaultUrl("/")
@UrlPattern("/?")
public class GooglePageMainPage extends Page<GooglePageMainPage> {


    public GooglePageMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[name='q']")
    private WebElement searchInputField;


    @FindBy(css = "[id='resultStats']")
    private WebElement resultStats;

    @FindBy(css =  "[id='search']")
    private WebElement resultBar;
    /**
     * Вводит текст в поле ввода поиска
     * @param text - текст
     */
    public GooglePageMainPage typeSearchText(String text) {
        logger("вводим текст в поле поиска");
        searchInputField.sendKeys(text);

        return this;
    }
    public GooglePageMainPage pressSearchButton(){
        logger("нажимаем кнопку поиска");
        searchInputField.sendKeys(Keys.RETURN);
        assertTrue("Результаты поиска отображаются на странице",
                standartWaiter.waitForCondition(ExpectedConditions
                        .visibilityOf(resultStats)));
        return this;
    }
    public GooglePageMainPage resultBarShouldExist(){
        logger("проверяем существование результатов поиска");
        assertTrue("Результаты поиска отображаются на странице",
                standartWaiter.waitForCondition(ExpectedConditions
                        .visibilityOf(resultBar)));
        return this;
    }

    public GooglePageMainPage open() {
        logger("Открывам страницу");
        return super.open();
    }

}
