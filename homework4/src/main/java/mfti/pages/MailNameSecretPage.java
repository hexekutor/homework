package mfti.pages;

import mfti.navigation.Url;
import mfti.navigation.UrlParam;
import mfti.navigation.UrlPattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;
@UrlParam(
        {
                @Url(name = "namesecret", url = "/%1"),
        }
)
@UrlPattern("/namesecret/?")
public class MailNameSecretPage extends Page<MailNameSecretPage> {

    @FindBy(css = "[class='input__field js-suggest__input']")
    private WebElement inputField;

    @FindBy(css = "[class='filter__item']")
    private List<WebElement> letterButton;

    @FindBy(css = "[class ='suggest__inner js-suggest__render']")
    private WebElement suggestMenu;

    @FindBy(css = "[name='g']")
    private WebElement sexTypes;

    private String letterOpenButtonSelector = "[class='filter__item filter__item_active']";
    private String suggestElementLocator = "//div[@class ='suggest__item js-suggest__item']";
    private String searchButtonLocator = "//button[@class ='button button_nowrap button_color_project']";
    private String searchResultsLocator = "//div[@class ='newsitem newsitem_vertical newsitem_special newsitem_border_bottom']";
    private String searchSecondResultLocator = "//div[@class ='article__item article__item_alignment_left article__item_html']";
    private String selectButtonLocator = "//div[@class ='dropdown dropdown_scrollable dropdown_scrollable dropdown_scrollable js-select js-module']";
    private String selectMenuLocator = "//div[@class ='suggest__inner js-select__options__list js-scrollable__view dropdown__scroll']";
    private String womanSelectElementLocator = "//div[@class ='suggest__item js-select__options__item' and text()='Женские']";
    public MailNameSecretPage(WebDriver driver) {
        super(driver);
    }

    public MailNameSecretPage open(String ... args) {
        logger("Открывам страницу");
        return super.open(args);
    }
    public MailNameSecretPage pressLetterButton(String letter){
        logger("клик по кнопке буквы");
        letterButton.stream()
                .filter((button) -> Objects.equals(button.getText(), letter))
                .findFirst()
                .get()
                .click();
        return this;
    }
    public MailNameSecretPage checkLetterButton(String letter){
        assertTrue(String.format("выбран список имен начинающихся с буквы %s", letter),
                standartWaiter.waitForCondition(ExpectedConditions
                        .textToBe(By.cssSelector(letterOpenButtonSelector), letter)));
        return this;
    }
    public MailNameSecretPage typeName(String text){
        logger("ввод имени");
        inputField.sendKeys(text);
        return this;
    }
    public MailNameSecretPage checkSuggestMenu(){
        logger("проверка показа выпадающего меню");
        assertTrue("показано выпадающее меню",
                standartWaiter.waitForCondition(ExpectedConditions
                        .visibilityOf(suggestMenu)));
        return this;
    }
    public MailNameSecretPage pressSuggestElement(){
        logger("проверка существования выборки имен");
        List<WebElement> suggestElements = driver.findElements(By.xpath(suggestElementLocator));
        assertTrue("в выпадающем меню есть выборка имен",!suggestElements.isEmpty());
        suggestElements.get(0).click();
        return this;
    }
    public MailNameSecretPage assertSearchButton(){
        logger("проверка кнопки поиска");
        driver.findElement(By.xpath(searchButtonLocator)).click();
        assertTrue("кнопка поиска работает",
                standartWaiter.waitForCondition(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath(searchResultsLocator))));
        return this;
    }
    public MailNameSecretPage pressSelectElement(){
        logger("клик на меню выбора пола");
        driver.findElement(By.xpath(selectButtonLocator)).click();
        assertTrue("показано меню выбора пола",
                standartWaiter.waitForCondition(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(selectMenuLocator))));
        return this;
    }
    public MailNameSecretPage assertSelect(){
        logger("проверка меню выбора пола");
        //Select sexTypes = new Select(sexTypeslocator);
        //List<WebElement> selectedOptions = sexTypes.getAllSelectedOptions();
        //selectedOptions.get(2).click();
        //Assert.assertEquals("Женские", sexTypes.getFirstSelectedOption().getText());
        driver.findElement(By.xpath(womanSelectElementLocator)).click();
        driver.findElement(By.xpath(searchButtonLocator)).click();
        assertTrue("проверка меню выбора пола",
                standartWaiter.waitForCondition((ExpectedConditions
                        .presenceOfElementLocated(By.xpath(searchSecondResultLocator)))));
        return this;
    }
}
