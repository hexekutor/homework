package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementIsNotClickableException;
import components.BaseComponent;
import components.Component;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertTrue;

@Component(css = "[class='sharelist__items']")
public class ShardingComponent extends BaseComponent<ShardingComponent> {
    private String nameButton = null;
    private String expectedUrlSubstring = null;

    public ShardingComponent(String nameButton, String expectedUrlSubstring){
        this.nameButton = nameButton;
        this.expectedUrlSubstring = expectedUrlSubstring;
    }

    private ElementsCollection buttonsCollection = $$("[class='sharelist__items'] a");
    private SelenideElement closeAddButton = $("[class='rb-button-close']");

    @Step("клик на кнопку шардинка")
    public ShardingComponent clickShardingButton(){
        SelenideElement shardingButton = buttonsCollection.stream()
                .filter(button -> Objects.equals(button.getText(), nameButton))
                .findFirst()
                .get();
        try{
            shardingButton.click();
        }catch(ElementIsNotClickableException e){
            closeAddButton.click();
            shardingButton.click();
        }

        return this;
    }
    @Step("провырка урла нового окна")
    public ShardingComponent checkNewWindowUrl(){
        String newWindow = WebDriverRunner.getWebDriver().getWindowHandles().stream()
                .filter(window -> !Objects.equals(WebDriverRunner.getWebDriver().getWindowHandle(), window))
                .findFirst()
                .get();
        Selenide.switchTo().window(newWindow);
        assertTrue("проверка url нового окна",
                WebDriverRunner.url().contains(expectedUrlSubstring));
        return this;
    }
}
