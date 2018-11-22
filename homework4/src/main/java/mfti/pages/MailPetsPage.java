package mfti.pages;

import mfti.navigation.DefaultUrl;
import mfti.navigation.Step;
import mfti.navigation.UrlPattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@UrlPattern("/news/")
@DefaultUrl("/news")
public class MailPetsPage extends Page<MailPetsPage> {
    private List<WebElement> newsList = null;
    private List<WebElement> newsListAfterPressShowMore = null;

    private String newsField = "[class='pypo-item js-pgng_item']";

    private String showMoreButton = "[class='button js-pgng_more_link']";

    public MailPetsPage(WebDriver driver) {
        super(driver);
    }
    @Step("Открывам страницу")
    public MailPetsPage open(String ... args) {
        logger("Открывам страницу");
        super.open(args);
        return this;
    }
    @Step("Открывам страницу")
    public MailPetsPage open() {
        logger("Открывам страницу");
        super.open();
        return this;
    }
    @Step("Проверяем открылась ли страница")
    public MailPetsPage pageShouldBeOpened() {
        logger("Проверяем URL страницы");
        super.pageShouldBeOpened();
        return this;
    }
    @Step("Проверяем URL страницы")
    public MailPetsPage assertUrl(){
        logger("Проверяем URL страницы");
        super.assertUrl();
        return this;
    }
    private List<WebElement> countNews(){

        return driver.findElements(By.cssSelector(newsField));
    }
    @Step("Считаем количество новостей до нажатия кнопки")
    public MailPetsPage countNewsBeforePressShowMore(){
        logger("Считаем количество новостей до нажатия кнопки");
        assertTrue("Выпадающее меню города не отображается на странице",
                standartWaiter.waitForCondition(ExpectedConditions
                        .attributeContains(By.cssSelector(newsField),
                                "class", "pypo-item js-pgng_item")));
        newsList = countNews();
        assertNotNull("новости должны быть на странице", newsList);
        return this;
    }
    @Step("Нажимаем кнопку \"показать еще\"")
    public MailPetsPage pressShowMoreButton(){
        logger("Нажимаем кнопку \"показать еще\"");
        driver.findElement(By.cssSelector(showMoreButton)).click();
        return this;
    }
    @Step("Считаем количество новостей после нажатия кнопки")
    public MailPetsPage countNewsAfterPressShowMore(){
        logger("Считаем количество новостей после нажатия кнопки");
        assertTrue("Выпадающее меню города не отображается на странице",
                standartWaiter.waitForCondition(ExpectedConditions
                        .numberOfElementsToBeMoreThan(By.cssSelector(newsField),
                                newsList.size())));
        newsListAfterPressShowMore = countNews();
        assertNotNull("новости должны быть на странице", newsList);
        return this;
    }
    public MailPetsPage checkEqualNewsAfterPressShowMore(){
        logger("проверяем что новости не изменились");
        newsListAfterPressShowMore = countNews();
        assertTrue(newsListAfterPressShowMore.containsAll(newsList));
        return this;
    }
    @Step("Проверяем изменилось ли количество новостей")
    public void checkButton(){
        logger("Проверяем изменилось ли количество новостей");
        assertTrue(String.valueOf(newsListAfterPressShowMore.size()) + " - количество новостей", newsListAfterPressShowMore.size() > newsList.size());
    }


}
