package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import components.BaseComponent;
import components.Component;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$x;

@Component(xpath = "//div[contains(@class,'pc-new-recipe-ingredient')]")
public class Ingridient extends BaseComponent<Ingridient> {
    private ElementsCollection dropdownButtonCollection = $$x("//div[contains(@class,'dropdown_scrollable')]");
    private ElementsCollection dropdownCollection = $$x("//div[contains(@class,'suggest__inner')]");
    private ElementsCollection suggestElementCollection = $$x("//div[contains(@class,'suggest__item')]");
    private String element = null;
    private Integer selectNum = null;
    public Ingridient(Integer selectNum, String element){
        this.selectNum = selectNum;
        this.element = element;
    }
    @Step("Открываем выпадающее меню")
    public Ingridient openSelect(){
        dropdownButtonCollection.get(selectNum).click();
        dropdownCollection.get(selectNum).shouldBe(Condition.visible);
        return this;
    }
    @Step("Выбираем элемент из выпадающего меню")
    public Ingridient chooseElement(){
        suggestElementCollection.stream()
                .filter(element -> element.is(Condition.visible))
                .filter(element -> Objects.equals(element.getText(), this.element))
                .findFirst()
                .get()
                .click();
        return this;
    }
    @Step("Проверяем что выбран нужный элемент из выпадающего меню")
    public Ingridient checkElement(){
        dropdownButtonCollection.get(selectNum).shouldHave(Condition.text(this.element));
        return this;
    }

}
