package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import navigation.DefaultUrl;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DefaultUrl("/")
public class MainPage extends PageObject<MainPage> {

    private ElementsCollection buttonsCollection = $$(
            "[class='input-group js-module'] button");
    private ElementsCollection channelsCollection = $$(
            "[class='p-channels__item']");
    private SelenideElement listView = $("[class='p-channels p-channels_list']");
    private SelenideElement gridView = $("[class='p-channels p-channels_grid']");

    @Step("Проверка переключения вида списка каналов в лист")
    public MainPage switchToListView(){
        buttonsCollection.get(1).click();
        listView.should(Condition.visible);
        channelsCollection.forEach(chanel -> chanel.should(Condition.visible));
        return this;
    }
    @Step("Проверка переключения вида списка каналов в сетку")
    public MainPage switchToGridView(){
        buttonsCollection.get(0).click();
        gridView.should(Condition.visible);
        channelsCollection.forEach(chanel -> chanel.should(Condition.visible));
        return this;
    }

}
