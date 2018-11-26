package mfti.pages;

import mfti.navigation.DefaultUrl;
import mfti.navigation.UrlPattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertTrue;

@UrlPattern("/news/")
@DefaultUrl("/news")
public class MailPetsPage extends Page<MailPetsPage> {
    private List<WebElement> newsList = null;
    private List<WebElement> newsListAfterPressShowMore = null;

    @FindBy(css = "[class='pypo-item js-pgng_item']")
    private WebElement newsField;

    private String newsFieldSelector = "[class='pypo-item js-pgng_item']";

    private String showMoreButtonSelector = "[class='button js-pgng_more_link']";

    public MailPetsPage(WebDriver driver) {
        super(driver);
    }
    public MailPetsPage open(String ... args) {
        logger("Открывам страницу");
        super.open(args);
        return this;
    }
    public MailPetsPage open() {
        logger("Открывам страницу");
        super.open();
        return this;
    }
    private List<WebElement> getNewsList(){
        return driver.findElements(By.cssSelector(newsFieldSelector));
    }
    public MailPetsPage countNewsBeforePressShowMore(){
        logger("Считаем количество новостей до нажатия кнопки");
        assertTrue("новости отображаются на странице",
                standartWaiter.waitForCondition(ExpectedConditions.visibilityOf(newsField)));
        newsList = getNewsList();
        return this;
    }
    public MailPetsPage pressShowMoreButton(){
        logger("Нажимаем кнопку \"показать еще\"");
        driver.findElement(By.cssSelector(showMoreButtonSelector)).click();
        return this;
    }
    public MailPetsPage countNewsAfterPressShowMore(){
        logger("Считаем количество новостей после нажатия кнопки");
        assertTrue("Проверяем изменилось ли количество новостей",
                standartWaiter.waitForCondition(ExpectedConditions
                        .numberOfElementsToBeMoreThan(By.cssSelector(newsFieldSelector),
                                newsList.size())));
        newsListAfterPressShowMore = getNewsList();
        return this;
    }
    public MailPetsPage checkEqualNewsAfterPressShowMore(){
        logger("проверяем что новости не изменились");
        newsListAfterPressShowMore = getNewsList();
        assertTrue(newsListAfterPressShowMore.containsAll(newsList));
        return this;
    }


}
