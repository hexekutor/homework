package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.BaseComponent;
import components.Component;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertTrue;

@Component(css = "[class='pm-menu']")
public class MenuElement extends BaseComponent<MenuElement> {
    private SelenideElement searchButton = $(By.xpath("//div[@class='pm-menu__center__inner']//span[@data-config-id='26']"));
    private SelenideElement searchForm = $(By.xpath("//div[@class='pm-menu__center__inner']//form"));
    @Step("кликаем по кнопке поиска")
    public MenuElement clickSearchButton(){
        searchButton.click();
        return this;
    }
    @Step("проверяем форму поиска")
    public MenuElement checkSearchForm(){
        searchForm.shouldBe(Condition.visible);
        return this;
    }
    @Step("проверяем что страница не обновилась")
    public MenuElement checkPageIsNotRefreshed(){
        assertTrue(searchForm.is(Condition.visible));
        return this;
    }

}
