package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.BaseComponent;
import components.Component;
import components.Frame;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;


@Component(xpath = "//div[@id='root']//div[contains(@class,'popup')]")
@Frame(css = "iframe[class='ag-popup__frame__layout__iframe']")
public class LoginFrame extends BaseComponent<LoginFrame> {
    private SelenideElement closeButton = $("[data-test-id='cross'] svg");

    @Step("Закрытие попапа")
    public LoginFrame clickCloseButton(){
        closeButton.click();
        closeButton.shouldNotBe(Condition.visible);
        return this;
    }

}
