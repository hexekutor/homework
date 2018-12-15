package actions;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class TextBlockActions {
    @Step("выделяем текст")
    public TextBlockActions selectText(SelenideElement textBlock){
        new Actions(WebDriverRunner.getWebDriver())
                .moveToElement(textBlock)
                .doubleClick()
                .build()
                .perform();
        return this;
    }
    @Step("открываем попап")
    public TextBlockActions openMistakePopup(){
        new Actions(WebDriverRunner.getWebDriver())
                .keyDown(Keys.CONTROL)
                .sendKeys(Keys.ENTER)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
        return this;
    }

}
