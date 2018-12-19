package pages;

import com.codeborne.selenide.WebDriverRunner;
import elements.Ingridient;
import elements.LoginFrame;
import io.qameta.allure.Step;
import navigation.DefaultUrl;

@DefaultUrl("/recipe/add/")
public class RecipePage extends PageObject<RecipePage> {

    @Step("Проверяем select ингридиента")
    public RecipePage checkSelect(Integer selectNum, String element){
        new Ingridient(selectNum, element)
                .openSelect()
                .chooseElement()
                .checkElement();
        return this;
    }
    @Step("Закрываем попап логина")
    public RecipePage closeLoginPopup(){
        new LoginFrame()
                .clickCloseButton();
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
        return this;
    }

}
