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
    private SelenideElement newsBlock = $(By.xpath("//div[contains(@class,'rb_nat') and not(contains(@class,'link-hdr')) ]"));
    private String newsLocator = "//a[contains(@class,'newsitem__title')]";
    private String columnHeaderSelector = "[class=\"hdr__inner\"]";
    @Step("Кликаем первую новость в выбранном блоке")
    public MainPage pressNews(String columnName){

        newsBlock.findAll(newsColumnSelector).stream()
                .filter(column -> Objects.equals(column.find(By.cssSelector(columnHeaderSelector)).getText(), columnName))
                .findFirst()
                .get()
                .find(By.xpath(newsLocator))
                .click();
        return this;
    }
    @Step("проверяем кнопку шардинга")
    public MainPage pressShardingButton(String buttonName, String expectedUrlSubstring){
        new ShardingComponent(buttonName, expectedUrlSubstring)
                .clickShardingButton()
                .checkNewWindowUrl();
        return this;
    }



}
