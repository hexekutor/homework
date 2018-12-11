package mfti.pages;

import mfti.navigation.DefaultUrl;
import mfti.navigation.Domain;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;


@Domain("https://pets.mail.ru")
@DefaultUrl("/how-to/nyanya-dlya-sobak-i-koshek-kak-podgotovitsya-k-raz/")
public class WeatherArticlePage extends Page<WeatherArticlePage>{

    public WeatherArticlePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[class='note__text breadcrumbs__text js-ago']")
    private WebElement date;

    public WeatherArticlePage open() {
        super.open();
        return this;
    }
    public WeatherArticlePage checkDate(){
        String dateString = date.getText();
        assertTrue("проверка правильности даты", dateString.matches("\\d+ [а-я]+ \\d+"));
        return this;
    }


}
