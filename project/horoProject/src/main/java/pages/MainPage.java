package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import navigation.DefaultUrl;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DefaultUrl("/horoscope/")
public class MainPage extends PageObject<MainPage> {
    private String horoscopeName = null;
    private ElementsCollection horoscopeLinks = $$(By.xpath("//div[contains(@class,'block')]//a[contains(@class,'hdr__text ')]"));
    private SelenideElement horoscopeHeader = $(By.xpath("//h1[@class='hdr__inner']"));

    public MainPage(String horoscopeName){
        this.horoscopeName = horoscopeName;
    }
    @Step("Открываем гороскоп")
    public MainPage clickHoroscope(){
        horoscopeLinks.stream()
                .filter(horoscope -> Objects.equals(horoscope.getText(), horoscopeName))
                .findFirst()
                .get()
                .click();
        return this;
    }
    @Step("Проверяем название открытого гороскопа")
    public MainPage checkHoroscopeTitle(){
        horoscopeHeader.shouldBe(Condition.text(horoscopeName));
        return this;
    }

}
