package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.BaseComponent;
import components.Component;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertTrue;

@Component(css = "[class='popup__layout']")
public class MistakePopup extends BaseComponent<MistakePopup> {
    String url = null;
    public MistakePopup(String url){
        this.url = url;
    }
    private ElementsCollection inputFields = $$("[class='input__field']");
    private SelenideElement closePopupButton = $("[class='icon icon_close js-popup_close popup__close']");
    @Step("проверяем поле URL")
    public MistakePopup checkUrlField(){
        inputFields.first().should(Condition.value(url));
        return this;
    }
    @Step("проверяем поле ошибки")
    public MistakePopup checkMistakeField(){
        String valueField = inputFields.last().getValue();
        assertTrue(valueField.matches(".*\\[.+].*"));
        return this;
    }
    @Step("закрываем попап")
    public MistakePopup closePopup(){
        closePopupButton.click();
        closePopupButton.shouldNotBe(Condition.visible);
        return this;
    }
}
