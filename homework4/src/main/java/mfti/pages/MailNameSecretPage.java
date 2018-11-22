package mfti.pages;

import mfti.navigation.Url;
import mfti.navigation.UrlParam;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertTrue;
@UrlParam(
        {
                @Url(name = "namesecret", url = "/%1"),
        }
)
public class MailNameSecretPage extends Page<MailNameSecretPage> {

    @FindBy(css = "[class='input__field js-suggest__input']")
    private WebElement inputField;

    private String letterButton = "a[class='filter__item'][href='/namesecret/a/']";
    private String letterOpenButton = "a[class='filter__item filter__item_active']";
    private String suggestMenu = "//div[@class ='suggest js-suggest__list' and @style ='display: block;']";
    private String suggestElement = "//div[@class ='suggest__item js-suggest__item']";
    private String searchButton = "//button[@class ='button button_nowrap button_color_project']";
    private String searchResults = "//div[@class ='newsitem newsitem_vertical newsitem_special newsitem_border_bottom']";
    private String selectButton = "//div[@class ='dropdown dropdown_scrollable dropdown_scrollable dropdown_scrollable js-select js-module']";
    private String selectMenu = "//div[@class ='suggest__inner js-select__options__list js-scrollable__view dropdown__scroll']";
    private String womanSelectElement = "//div[@class ='suggest__item js-select__options__item' and text()='Женские']";
    public MailNameSecretPage(WebDriver driver) {
        super(driver);
    }

    public MailNameSecretPage open(String ... args) {
        logger("Открывам страницу");
        super.open(args);
        return this;
    }
    public MailNameSecretPage open() {
        logger("Открывам страницу");
        super.open();
        return this;
    }
    public MailNameSecretPage pageShouldBeOpened() {
        logger("Проверяем открылась ли страница");
        super.pageShouldBeOpened();
        return this;
    }
    public MailNameSecretPage assertUrl(){
        logger("Проверяем URL страницы");
        super.assertUrl();
        return this;
    }
    public MailNameSecretPage pressLetterButton(){
        logger("клик по кнопке буквы");
        driver.findElement(By.cssSelector(letterButton)).click();
        return this;
    }
    public void checkLetterButton(){
        logger("проверка выбора списка имен начинающихся с буквы А");
        assertTrue("выбран список имен начинающихся с буквы А",
                standartWaiter.waitForCondition(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.cssSelector(letterOpenButton))));
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
                        .presenceOfAllElementsLocatedBy(By.xpath(suggestMenu))));
        return this;
    }
    public MailNameSecretPage pressSuggestElement(){
        logger("проверка существования выборки имен");
        List<WebElement> suggestElements = driver.findElements(By.xpath(suggestElement));
        assertTrue("в выпадающем меню есть выборка имен",!suggestElements.isEmpty());
        suggestElements.get(0).click();
        return this;
    }
    public MailNameSecretPage assertSearchButton(){
        logger("проверка кнопки поиска");
        driver.findElement(By.xpath(searchButton)).click();
        assertTrue("кнопка поиска работает",
                standartWaiter.waitForCondition(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath(searchResults))));
        return this;
    }
    public MailNameSecretPage pressSelectElement(){
        logger("клик на меню выбора пола");
        driver.findElement(By.xpath(selectButton)).click();
        assertTrue("показано меню выбора пола",
                standartWaiter.waitForCondition(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath(selectMenu))));
        return this;
    }
    public MailNameSecretPage assertSelect(){
        logger("проверка меню выбора пола");
        driver.findElement(By.xpath(womanSelectElement)).click();
        driver.findElement(By.xpath(searchButton)).click();
        assertTrue("проверка меню выбора пола",
                standartWaiter.waitForCondition(ExpectedConditions.not((ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath(searchResults))))));
        return this;
    }
}
