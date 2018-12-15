package pages;

import com.codeborne.selenide.SelenideElement;
import elements.ShardingComponent;
import io.qameta.allure.Step;
import navigation.DefaultUrl;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

@DefaultUrl("/")
public class MainPage extends PageObject<MainPage> {
    private String newsColumnSelector = "[class='cols__inner']";
    private SelenideElement newsBlock = $("[class='block block_separated_top rb_nat']");
    private String newsSelector = "[class='newsitem__title link-holder']";
    private String columnHeaderSelector = "[class=\"hdr__inner\"]";
    @Step("Кликаем первую новость в выбранном блоке")
    public MainPage pressNews(String columnName){

        newsBlock.findAll(newsColumnSelector).stream()
                .filter(column -> Objects.equals(column.find(By.cssSelector(columnHeaderSelector)).getText(), columnName))
                .findFirst()
                .get()
                .find(By.cssSelector(newsSelector))
                .click();
        return this;
    }
    @Step("проверяем кнопку шардинга")
    public MainPage pressShardingButton(String buttonName){
        new ShardingComponent(buttonName)
                .clickShardingButton()
                .checkNewWindowUrl();
        return this;
    }



}
