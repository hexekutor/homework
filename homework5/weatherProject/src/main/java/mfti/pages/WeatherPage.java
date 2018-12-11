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

    @FindBy(css = "[class='js-input pm-toolbar__search__input  pm-toolbar__search__input_not-expandable pm-toolbar__search__input_not-adaptive']")
    private WebElement searchInputField;

    @FindBy(css = "[type='submit']")
    private WebElement searchButton;

    @FindBy(css = "[class='icon__inner icon__inner_add']")
    private WebElement favouritesButton;

    @FindBy(css = "[class='city-select__item__city']")
    private List<WebElement> favouritesCity;

    @FindBy(css = "[class='js-text-inner pm-toolbar__button__text__inner  pm-toolbar__button__text__inner_noicon pm-toolbar__button__text__inner_dropdown']")
    private WebElement citiesFavourities;

    private String cityHeaderSelector = "[class='js-text-inner pm-toolbar__button__text__inner  pm-toolbar__button__text__inner_noicon pm-toolbar__button__text__inner_dropdown']";

    public WeatherPage open() {
        super.open();
        return this;
    }
    public WeatherPage checkCityIsNotInFavourites(){
        Actions actions = new Actions(driver);
        actions.moveToElement(citiesFavourities)
                .build()
                .perform();
        assertTrue("Города в избранном видны", standartWaiter.
                waitForCondition(ExpectedConditions.visibilityOf(favouritesCity.get(0))));
        List<String> cities = new ArrayList<>();
        favouritesCity.forEach(city -> cities.add(city.getText()));
        assertTrue("Города нет в избранном", !cities.contains(city));
        return this;
    }
    public WeatherPage checkCityIsInFavourites(){
        Actions actions = new Actions(driver);
        actions.moveToElement(citiesFavourities)
                .build()
                .perform();
        assertTrue("Города в избранном видны", standartWaiter.
                waitForCondition(ExpectedConditions.visibilityOf(favouritesCity.get(0))));
        List<String> cities = new ArrayList<>();
        favouritesCity.forEach(city -> cities.add(city.getText()));
        assertTrue("Город есть в избранном", cities.contains(city));
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
                        .textToBe(By.cssSelector(cityHeaderSelector), city)));
        return this;
    }
    public WeatherPage pressFavouritesButton(){
        favouritesButton.click();
        return this;
    }
}
