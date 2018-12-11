package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import navigation.DefaultUrl;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertTrue;

@DefaultUrl("/open/")
public class MainPage extends PageObject<MainPage> {
    private SelenideElement openedPage = $("[class='f_left t80'] b");
    private ElementsCollection avatarCollection = $$("[class='q--li--ava small-avatar']");

    private String userId = null;
    @Step("Кликаем на аватарку пользователя")
    public MainPage pressUserAvatar(){
        userId = avatarCollection.get(0).attr("data-user");
        System.out.println(avatarCollection.get(0).attr("style"));
        avatarCollection.get(0).click();
        assertTrue(WebDriverRunner.url().contains(userId));
        return this;
    }

}
