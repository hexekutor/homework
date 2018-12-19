package actions;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$x;

public class MenuCityActions {
    private SelenideElement menu = $x("//span[contains(@class,'pm-toolbar__button__inner')]");
    @Step("Открываем меню")
    public MenuCityActions openMenu(){
        new Actions(WebDriverRunner.getWebDriver())
                .moveToElement(menu)
                .build()
                .perform();
        return this;
    }
}
