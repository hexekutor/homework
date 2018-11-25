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
    private WebElement inputFieldLocator;

    @FindBy(css = "[class='filter__item']")
    private List<WebElement> letterButtonLocator;

    @FindBy(css = "[class ='suggest__inner js-suggest__render']")
    private WebElement suggestMenuLocator;

    @FindBy(css = "[name='g']")
    private WebElement sexTypeslocator;

    private String letterOpenButtonSelector = "[class='filter__item filter__item_active']";
    private String suggestElementSelector = "//div[@class ='suggest__item js-suggest__item']";
    private String searchButtonSelector = "//button[@class ='button button_nowrap button_color_project']";
    private String searchResultsSelector = "//div[@class ='newsitem newsitem_vertical newsitem_special newsitem_border_bottom']";
    private String searchSecondResultSelector = "//div[@class ='article__item article__item_alignment_left article__item_html']";
    private String selectButtonSelector = "//div[@class ='dropdown dropdown_scrollable dropdown_scrollable dropdown_scrollable js-select js-module']";
    private String selectMenuSelector = "//div[@class ='suggest__inner js-select__options__list js-scrollable__view dropdown__scroll']";
    private String womanSelectElementSelector = "//div[@class ='suggest__item js-select__options__item' and text()='Женские']";
    public MailNameSecretPage(WebDriver driver) {
        super(driver);
    }

    public MailNameSecretPage open(String ... args) {
        logger("Открывам страницу");
        return super.open(args);
    }
    public MailNameSecretPage open() {
        logger("Открывам страницу");
        return super.open();
    }
    public MailNameSecretPage pressLetterButton(){
        logger("клик по кнопке буквы");
        WebElement letter_A_Button = null;
        for(WebElement letterButton : letterButtonLocator){
            if(Objects.equals(letterButton.findElement(By.cssSelector("[class='filter__text']")).getText(), "А")){
                letter_A_Button = letterButton;
                break;
            }
        }
        letter_A_Button.click();
        return this;
    }
    public void checkLetterButton(){
        logger("проверка выбора списка имен начинающихся с буквы А");
        assertTrue("выбран список имен начинающихся с буквы А",
                standartWaiter.waitForCondition(ExpectedConditions
                        .textToBe(By.cssSelector(letterOpenButtonSelector), "А")));
    }
    public MailNameSecretPage typeName(String text){
        logger("ввод имени");
        inputFieldLocator.sendKeys(text);
        return this;
    }
    public MailNameSecretPage checkSuggestMenu(){
        logger("проверка показа выпадающего меню");
        assertTrue("показано выпадающее меню",
                standartWaiter.waitForCondition(ExpectedConditions
                        .visibilityOf(suggestMenuLocator)));
        return this;
    }
    public MailNameSecretPage pressSuggestElement(){
        logger("проверка существования выборки имен");
        List<WebElement> suggestElements = driver.findElements(By.xpath(suggestElementSelector));
        assertTrue("в выпадающем меню есть выборка имен",!suggestElements.isEmpty());
        suggestElements.get(0).click();
        return this;
    }
    public MailNameSecretPage assertSearchButton(){
        logger("проверка кнопки поиска");
        driver.findElement(By.xpath(searchButtonSelector)).click();
        assertTrue("кнопка поиска работает",
                standartWaiter.waitForCondition(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath(searchResultsSelector))));
        return this;
    }
    public MailNameSecretPage pressSelectElement(){
        logger("клик на меню выбора пола");
        driver.findElement(By.xpath(selectButtonSelector)).click();
        assertTrue("показано меню выбора пола",
                standartWaiter.waitForCondition(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(selectMenuSelector))));
        return this;
    }
    public MailNameSecretPage assertSelect(){
        logger("проверка меню выбора пола");
        //Select sexTypes = new Select(sexTypeslocator);
        //List<WebElement> selectedOptions = sexTypes.getAllSelectedOptions();
        //selectedOptions.get(2).click();
        //Assert.assertEquals("Женские", sexTypes.getFirstSelectedOption().getText());
        driver.findElement(By.xpath(womanSelectElementSelector)).click();
        driver.findElement(By.xpath(searchButtonSelector)).click();
        assertTrue("проверка меню выбора пола",
                standartWaiter.waitForCondition((ExpectedConditions
                        .presenceOfElementLocated(By.xpath(searchSecondResultSelector)))));
        return this;
    }
}
