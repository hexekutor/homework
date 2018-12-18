package pages;

import navigation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import wait.StandartWaiter;

import java.util.Objects;

import static org.junit.Assert.assertThat;

/**
 * Base Page Pattern
 * @param <T>
 */
public class Page<T> {

    private static final Logger rootLogger = LogManager.getRootLogger();
    private String openedDomain = null;
    private String openedUrl = null;
    protected WebDriver driver = null;
    protected String domainTemplate = "http[s]?://.*?(?::\\d+)?";

    protected StandartWaiter standartWaiter = null;

    public Page(WebDriver driver) {
        this.driver = driver;
        standartWaiter = new StandartWaiter(driver);
        PageFactory.initElements(driver, this);

    }
    protected T open(String ... args) {
        String url = buildUrl(getUrlWithParam(args[0]), args);
        openedDomain = getDomain();
        openedUrl = url;
        driver.get(openedDomain + url);
        assertUrl();
        return (T) this;
    }
    protected T open() {
        openedDomain = getDomain();
        openedUrl = getDefaultUrl();
        driver.get(openedDomain + openedUrl);
        assertUrl();
        return (T) this;
    }
    private String buildUrl(String urlTemplate, String ... args){
        String url = null;
        for(int i = 1; i < args.length; i++) {
            url = urlTemplate.replace("%" + (i), args[i]);
        }
        return url;
    }
    private String getUrlWithParam(String name){
        Class<? extends Page> clazz = getClass();

        if(clazz.isAnnotationPresent(UrlParam.class)) {
            UrlParam annotation = clazz.getAnnotation(UrlParam.class);
            for(Url url : annotation.value()){
                if(Objects.equals(url.name(), name)){
                    return url.url();
                }

            }

        }
        return "";
    }
    private String getDefaultUrl() {
        Class<? extends Page> clazz = getClass();

        if(clazz.isAnnotationPresent(DefaultUrl.class)) {
            DefaultUrl annotation = clazz.getAnnotation(DefaultUrl.class);
            return annotation.value();
        }

        return "";
    }
    void logger(String message){
        rootLogger.info(message);
    }
    private T assertUrl() {
        logger("Проверяем URL страницы");
        Class<? extends Page> clazz = getClass();

        if(clazz.isAnnotationPresent(UrlPattern.class)) {
            UrlPattern annotation = clazz.getAnnotation(UrlPattern.class);
            assertThat("Проверка Url страницы", driver.getCurrentUrl(), Matchers.matchesPattern(openedDomain + annotation.value()));
        }else{
            assertThat(String.format("Должна быть открыта страница %s", getDefaultUrl()),
                    driver.getCurrentUrl(),
                    StringContains.containsString(openedDomain + openedUrl));
        }

        return (T) this;
    }
    private String getDomain() {
        Class<? extends Page> clazz = getClass();

        if(clazz.isAnnotationPresent(Domain.class)) {
            Domain annotation = clazz.getAnnotation(Domain.class);
            return annotation.value();
        }

        return System.getProperty("webdriver.base.url");
    }
}
