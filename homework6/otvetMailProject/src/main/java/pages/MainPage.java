package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import navigation.DefaultUrl;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertTrue;

@DefaultUrl("/open/")
public class MainPage extends PageObject<MainPage> {
    private ElementsCollection avatarCollection = $$(By.xpath("//a[contains(@class,'q--li--ava')]"));

    private String userId = null;
    @Step("Кликаем на аватарку пользователя")
    public MainPage pressUserAvatar(){
        userId = avatarCollection.get(0).attr("data-user");
        avatarCollection.get(0).click();
        assertTrue("проверям что открылась страница нужного пользователя", WebDriverRunner.url().contains(userId));
        return this;
    }
}
