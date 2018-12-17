package mfti.pages;

import mfti.calendar.Buttons;
import mfti.calendar.Month;
import mfti.navigation.DefaultUrl;
import mfti.navigation.Domain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertTrue;

@Domain("https://pogoda.mail.ru")
@DefaultUrl("/prognoz/easter_island/10-june/#2015")
public class WeatherCalendarPage extends Page<WeatherCalendarPage>{

    @FindBy(css = "[class='dropdown__field']")
    private WebElement calendarButton;

    @FindBy(css = "[class='dropdown__box']")
    private WebElement calendar;

    @FindBy(xpath = "//span[contains(@class,'calendar__month')]")
    private WebElement month;

    @FindBy(xpath = "//i[contains(@class,'icon_less')]")
    private WebElement previousMonthButton;

    @FindBy(xpath = "//i[contains(@class,'icon_more')]")
    private WebElement nextMonthButton;

    @FindBy(css = "[class='calendar__cell calendar__cell_enabled']")
    private List<WebElement> dayButtonList;

    @FindBy(css = "[class='heading heading_minor heading_line']")
    private WebElement header;


    private String monthSelector = month.toString();
    private String monthString = null;
    private  String dayString = null;

    public WeatherCalendarPage(WebDriver driver) {
        super(driver);
    }

    public WeatherCalendarPage open() {
        super.open();
        return this;
    }
    public WeatherCalendarPage pressCalendarButton(){
        calendarButton.click();
        checkCalendarToBeOpened();
        return this;
    }
    private void checkCalendarToBeOpened(){
        assertTrue("Календарь отображается на странице", standartWaiter.
                waitForCondition(ExpectedConditions.visibilityOf(calendar)));
    }
    public WeatherCalendarPage pressButton(Buttons button){
        checkCalendarToBeOpened();
        monthString = month.getText();
        if(button == Buttons.NEXT)
            nextMonthButton.click();
        else
            previousMonthButton.click();
        return this;
    }
    public WeatherCalendarPage checkChangingMonth(Buttons button){
        assertTrue("Месяц изменился", standartWaiter.
                waitForCondition(ExpectedConditions.not(
                        ExpectedConditions.textToBe(
                                By.cssSelector(monthSelector), monthString))));
        if(button == Buttons.NEXT)
            assertTrue("Месяц поменялся на следующий",
                    Month.checkMonthChangesToNext(
                            monthString, month.getText()));
        else
            assertTrue("Месяц поменялся на предыдущий",
                    Month.checkMonthChangesToPrevious(
                            monthString, month.getText()));
        monthString = null;
        return this;
    }
    public WeatherCalendarPage pressDayButton(Integer day){
        checkCalendarToBeOpened();

        monthString = month.getText();
        monthString = monthString.substring(0, monthString.length() - 1);
        dayString = Integer.toString(day);

        dayButtonList.stream()
                        .filter(calendarDay -> Integer.parseInt(calendarDay.getText()) == day)
                        .findFirst()
                        .get()
                        .click();
        return this;
    }
    public WeatherCalendarPage checkDayButton(){
        assertTrue("переход на другую страницу", standartWaiter.
                waitForCondition(ExpectedConditions.invisibilityOf(calendar)));
        assertTrue("Показана страница с нужной датой", header.getText().
                contains(dayString + " " + monthString.toUpperCase()));
        return this;
    }
}
