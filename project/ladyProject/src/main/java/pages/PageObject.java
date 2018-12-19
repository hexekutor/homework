package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import navigation.DefaultUrl;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertTrue;

public class PageObject<T> {
    private static WebDriver driver = null;
    static {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://lady.mail.ru";
    }

    private SelenideElement header = $("h1");

    public T headerShouldBe(String header) {
        this.header
                .shouldBe(text(header));
        return (T) this;
    }

    public T urlMatchesShouldBe(String pattern) {
        assertTrue("Url invalid",
                WebDriverRunner
                .url().matches(pattern));
        return (T) this;
    }
    @Step("Открытие страницы")
    public T open() {
        Selenide.open(getDefaultUrl());
        return (T) this;
    }

    private String getDefaultUrl() {
        Class<? extends PageObject> clazz = getClass();

        if(clazz.isAnnotationPresent(DefaultUrl.class)) {
            DefaultUrl annotation = clazz.getAnnotation(DefaultUrl.class);
            return annotation.value();
        }

        return "";
    }

}
