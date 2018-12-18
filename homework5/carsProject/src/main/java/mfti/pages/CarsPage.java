package mfti.pages;

import mfti.navigation.DefaultUrl;
import mfti.navigation.Domain;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertTrue;

@Domain("https://auto.mail.ru")
@DefaultUrl("/catalog/ford/focus/iii_restailing/sedan/")
public class CarsPage extends Page<CarsPage>{

    @FindBy(css = "[class='margin_bottom_5'] a")
    private List<WebElement> parametersList;

    @FindBy(xpath = "//div[contains(@class,'overlay__content')]")
    private WebElement popup;

    @FindBy(xpath = "//div[@class='popup__wrapper']/div/span")
    private WebElement popupHeader;

    @FindBy(xpath = "//div[@class='overlay__inner']//span[contains(@class,'icon_close')]")
    private WebElement closeButton;

    public CarsPage(WebDriver driver) {
        super(driver);
    }

    public CarsPage open() {
        super.open();
        return this;
    }
    public CarsPage pressParameter(String parameter){
        parametersList.stream()
                .filter(param -> param.getText().contains(parameter))
                .findFirst()
                .get()
                .click();

        return this;
    }
    public CarsPage checkPopupOpened(String parameter){
        assertTrue("попап отображается на странице", standartWaiter.waitForCondition(
                ExpectedConditions.visibilityOf(popup)));
        assertTrue("проверка заголовка попапа", popupHeader.getText().contains(parameter));
        return this;
    }
    public CarsPage closePopup(){
        closeButton.click();
        assertTrue("закрытие попапа", standartWaiter.waitForCondition(
                ExpectedConditions.invisibilityOf(popup)));
        return this;
    }
    public CarsPage checkPopupClosed(){
        assertTrue("закрытие попапа", standartWaiter.waitForCondition(
                ExpectedConditions.invisibilityOf(popup)));
        return this;
    }
}
