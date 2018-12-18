package mfti.pages;

import mfti.navigation.DefaultUrl;
import mfti.navigation.Domain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


@Domain("https://pogoda.mail.ru")
@DefaultUrl("/")
public class WeatherPage extends Page<WeatherPage> {
    public WeatherPage(WebDriver driver) {
        super(driver);
    }
    private String city = "Нью-Йорк";

    @FindBy(xpath = "//input[contains(@class,'pm-toolbar__search__input')]")
    private WebElement searchInputField;

    @FindBy(css = "[type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[contains(@class,'icon__inner_add')]")
    private WebElement favouritesButton;

    @FindBy(css = "[class='city-select__item__city']")
    private List<WebElement> favouritesCity;

    @FindBy(xpath = "//span[contains(@class,'js-text-inner')]")
    private WebElement citiesFavourities;

    private String cityLocator = "//span[contains(@class,'js-text-inner')]";

    public WeatherPage open() {
        super.open();
        return this;
    }
    public WeatherPage checkCityIsNotInFavourites(){
        List<String> cities = new ArrayList<>();
        favouritesCity.forEach(city -> cities.add(city.getText()));
        assertTrue("Города нет в избранном", !cities.contains(city));
        return this;
    }
    public WeatherPage checkCityIsInFavourites(){
        List<String> cities = new ArrayList<>();
        favouritesCity.forEach(city -> cities.add(city.getText()));
        assertTrue("Город есть в избранном", cities.contains(city));
        return this;
    }
    public WeatherPage openFavouritesList(){
        new Actions(driver)
                .moveToElement(citiesFavourities)
                .build()
                .perform();
        return this;
    }
    public WeatherPage checkFavourtesListOpened(){
        assertTrue("Города в избранном видны", standartWaiter.
                waitForCondition(ExpectedConditions.visibilityOf(favouritesCity.get(0))));
        return this;
    }
    public WeatherPage insertSearchField(){
        searchInputField.sendKeys(city);
        return this;
    }
    public WeatherPage pressSearchButton(){
        searchButton.click();
        assertTrue("Город отображается на странице",
                standartWaiter.waitForCondition(ExpectedConditions
                        .textToBe(By.xpath(cityLocator), city)));
        return this;
    }
    public WeatherPage pressFavouritesButton(){
        favouritesButton.click();
        return this;
    }
}
