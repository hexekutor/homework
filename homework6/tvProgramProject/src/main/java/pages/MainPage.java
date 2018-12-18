package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import navigation.DefaultUrl;
import org.openqa.selenium.By;
import view.View;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DefaultUrl("/")
public class MainPage extends PageObject<MainPage> {

    private ElementsCollection buttonsCollection = $$(
            "[class='input-group__item'] button");
    private ElementsCollection channelsCollection = $$(
            "[class='p-channels__item']");
    private SelenideElement listView = $(By.xpath("//div[contains(@class,'p-channels_list')]"));
    private SelenideElement gridView = $(By.xpath("//div[contains(@class,'p-channels_grid')]"));

    @Step("Переключение вида списка каналов")
    public MainPage switchView(View view){
        if(view == View.LIST)
            buttonsCollection.get(1).click();
        else
            buttonsCollection.get(0).click();
        return this;
    }
    @Step("Проверка переключения вида списка каналов")
    public MainPage checkView(View view){
        if(view == View.LIST)
            listView.should(Condition.visible);
        else
            gridView.should(Condition.visible);
        channelsCollection.forEach(chanel -> chanel.should(Condition.visible));
        return this;
    }

}
