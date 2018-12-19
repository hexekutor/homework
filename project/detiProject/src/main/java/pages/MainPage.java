package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import navigation.DefaultUrl;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertTrue;

@DefaultUrl("/")
public class MainPage extends PageObject<MainPage> {
    private String newsTitle = null;
    private String url = null;
    private SelenideElement newsBlock = $(By.xpath("//div[contains(@class,'daynews__item_big')]"));
    private SelenideElement newsHeader = $(By.xpath("//h1[@class='hdr__inner']"));
    private SelenideElement newsTitleElement = $(By.xpath("//div[contains(@class,'daynews__item_big')]//span[contains(@class,'photo__title')]"));

    @Step("Кликаем первую новость в  блоке")
    public MainPage pressNews(){
        url = WebDriverRunner.url();
        newsTitle = newsTitleElement.getText();
        newsBlock.click();
        return this;
    }
    @Step("Проверяем заголовок новости")
    public MainPage checkNewsTitle(){
        newsHeader.should(Condition.visible);
        assertTrue(Objects.equals(newsHeader.getText(), newsTitle));
        return this;
    }
    @Step("Проверяем URL страницы")
    public MainPage checkUrl(){
        assertTrue(WebDriverRunner.url().matches(url + "article/.+/"));
        return this;
    }




}
