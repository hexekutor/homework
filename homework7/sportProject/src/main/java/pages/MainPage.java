package pages;

import actions.TextBlockActions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import elements.MenuElement;
import elements.MistakePopup;
import io.qameta.allure.Step;
import navigation.DefaultUrl;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DefaultUrl("/")
public class MainPage extends PageObject<MainPage> {
    private SelenideElement newsBlock = $(By.xpath("//td[@class='daynews__main']/div"));
    private ElementsCollection newsText = $$("[class='article js-module'] p");
    @Step("Кликаем на новость в телевизоре")
    public MainPage pressNews(){
        newsBlock.click();
        newsText.first().shouldBe(Condition.visible);
        return this;
    }
    @Step("Выделяем текст в блоке и открываем попап")
    public MainPage selectText(){
        new TextBlockActions()
                .selectText(newsText.first())
                .openMistakePopup();
        return this;
    }
    @Step("проверяем попап")
    public MainPage checkPopup(){
        new MistakePopup(WebDriverRunner.url())
                .checkUrlField()
                .checkMistakeField()
                .closePopup();
        return this;
    }
    @Step("проверяем кнопку поиска")
    public MainPage checkSearchButton(){
        new MenuElement()
                .clickSearchButton()
                .checkSearchForm()
                .clickSearchButton()
                .checkPageIsNotRefreshed();
        return this;
    }
}
