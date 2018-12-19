package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import navigation.DefaultUrl;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.Assert.assertTrue;

@DefaultUrl("/")
public class MainPage extends PageObject<MainPage> {
    private ElementsCollection newsTitleCollection = $$x("//div[contains(@class,'pypo-feed')]//a[contains(@class,'pypo-item__title')]");
    private SelenideElement showMoreButton = $x("//div[contains(@class,'pypo-feed__actions')]//button[contains(@class,'button')]");

    private List<String> newsTitles = null;

    @Step("кликаем по кнопке \"Показать еще\"")
    public MainPage clickShowMoreButton(){
        newsTitles = newsTitleCollection.texts();
        showMoreButton.click();
        return this;
    }
    @Step("кликаем по кнопке \"Показать еще\"")
    public MainPage checkShowMoreButton(){
        newsTitleCollection.shouldBe(CollectionCondition.sizeGreaterThan(newsTitles.size()));
        List<String> newsListAfterClick = newsTitleCollection.texts();
        assertTrue("проверяем что прежние новости не изменились", newsListAfterClick.containsAll(newsTitles));
        return this;
    }



}
