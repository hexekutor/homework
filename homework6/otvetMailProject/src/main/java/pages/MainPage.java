package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import navigation.DefaultUrl;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

@DefaultUrl("/open/")
public class MainPage extends PageObject<MainPage> {
    private SelenideElement openedPage = $("[class='f_left t80'] b");
    private ElementsCollection avatarCollection = $$("[class='q--li--ava small-avatar']");

    private String userId = null;
    @Step("Кликаем на аватарку пользователя")
    public MainPage pressUserAvatar(){
        userId = avatarCollection.get(0).attr("data-user");
        avatarCollection.get(0).click();
        assertTrue("проверям что открылась страница нужного пользователя", WebDriverRunner.url().contains(userId));
        assertTrue("проверяем что страница открылась без ошибок", getCodeHttpRequest() == 200);
        return this;
    }
    private int getCodeHttpRequest() {
        Response response = given().relaxedHTTPSValidation().when().get(WebDriverRunner.url(), new HashMap<>());
        return response.getStatusCode();
    }



}
