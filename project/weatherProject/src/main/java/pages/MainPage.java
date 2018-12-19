package pages;

import com.codeborne.selenide.WebDriverRunner;
import elements.MenuPrognozElement;
import io.qameta.allure.Step;
import navigation.DefaultUrl;

import static org.junit.Assert.assertTrue;

@DefaultUrl("/")
public class MainPage extends PageObject<MainPage> {
    private String url;
    @Step("кликаем по кнопке \"Показать еще\"")
    public MainPage checkMenu(){
        url = WebDriverRunner.url();
        new MenuPrognozElement()
                .openMenu()
                .checkMunu()
                .pressCityInMenu(0);
        return this;
    }
    @Step("кликаем по кнопке \"Показать еще\"")
    public MainPage checkCityUrl(){
        assertTrue(WebDriverRunner.url().matches("https://pogoda.mail.ru/prognoz/.+"));
        return this;
    }



}
