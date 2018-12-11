package pages;

import navigation.DefaultUrl;
import navigation.Domain;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;


@Domain("https://pets.mail.ru")
@DefaultUrl("/how-to/nyanya-dlya-sobak-i-koshek-kak-podgotovitsya-k-raz/")
public class PetsArticlePage extends Page<PetsArticlePage>{

    public PetsArticlePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[class='note__text breadcrumbs__text js-ago']")
    private WebElement date;

    public PetsArticlePage open() {
        super.open();
        return this;
    }
    public PetsArticlePage checkDate(){
        String dateString = date.getText();
        assertTrue("проверка правильности даты", dateString.matches("\\d+ [а-я]+ \\d+"));
        return this;
    }


}
