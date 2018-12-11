package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import navigation.DefaultUrl;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DefaultUrl("/Rating/All/Today/Visitors/")
public class MainPage extends PageObject<MainPage> {
    private SelenideElement openedPage = $("[class='f_left t80'] b");
    private ElementsCollection navigationLinks = $$("[class='f_left t80'] a");

    private void pressButton(String buttonText){
        navigationLinks.stream()
                .filter(links -> links.getText().contains(buttonText))
                .findFirst()
                .get()
                .click();
    }
    private Integer getPageNumber(){
        return Integer.parseInt(openedPage.getText().substring(2, openedPage.getText().length() - 2));
    }
    @Step("Проверка перехода на определенную страницу")
    public MainPage goToPage(Integer page){
        pressButton(Integer.toString(page));
        openedPage.shouldHave(Condition.text(Integer.toString(page)));
        return this;
    }
    @Step("Проверка перехода на предыдущую страницу")
    public MainPage goToPreviousPage(){
        Integer beforePage = getPageNumber();
        pressButton("Назад");
        openedPage.shouldHave(Condition.text(Integer.toString(beforePage - 1)));
        return this;
    }
    @Step("Проверка перехода на следующую страницу")
    public MainPage goToNextPage(){
        Integer beforePage = getPageNumber();
        pressButton("Далее");
        openedPage.shouldHave(Condition.text(Integer.toString(beforePage + 1)));
        return this;
    }
    @Step("Проверка перехода на прошлую десятку страниц")
    public MainPage goBackTenPages(){
        Integer beforePage = getPageNumber();
        pressButton("«");
        openedPage.shouldHave(Condition.text(Integer.toString((beforePage / 10 - 1) * 10 + 1)));
        return this;
    }
    @Step("Проверка перехода на следующую десятку страниц")
    public MainPage goNextTenPages(){
        Integer beforePage = getPageNumber();
        pressButton("»");
        openedPage.shouldHave(Condition.text(Integer.toString((beforePage / 10 + 1) * 10 + 1 )));
        return this;
    }
}
