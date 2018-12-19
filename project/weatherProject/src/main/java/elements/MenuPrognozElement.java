package elements;

import actions.MenuCityActions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.BaseComponent;
import components.Component;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Component(xpath = "//span[contains(@class,'pm-toolbar__button__inner')]")
public class MenuPrognozElement extends BaseComponent<MenuPrognozElement> {
    private ElementsCollection menuCities = $$x("//a[contains(@class,'city-select__item__link') and contains(@href,'prognoz')]");
    private SelenideElement menu = $x("//span[contains(@class,'pm-toolbar__dropdown__col')]");
    private SelenideElement menuInstruction = $x("//span[contains(@class,'icon_fav-flag-small')]");
    private SelenideElement userCity = $x("//span[contains(@class,'city-select__item__here')]");
    @Step("Открываем меню")
    public MenuPrognozElement openMenu(){
        new MenuCityActions()
                .openMenu();
        menu.shouldBe(Condition.visible);
        return this;
    }
    @Step("Проверка элементов в меню")
    public MenuPrognozElement checkMunu(){
        menuCities.forEach(city -> city.shouldBe(Condition.visible));
        menuInstruction.shouldBe(Condition.visible);
        userCity.shouldBe(Condition.visible);
        return this;
    }
    @Step("клик по городу в меню")
    public MenuPrognozElement pressCityInMenu(int cityNumber){
        menuCities.get(cityNumber).click();
        menu.shouldNotBe(Condition.visible);
        return this;
    }

}
