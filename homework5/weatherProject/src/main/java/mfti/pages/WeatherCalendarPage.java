package mfti.pages;

import mfti.month.Month;
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

    @FindBy(css = "[class='calendar__month js-calendar_month']")
    private WebElement month;

    @FindBy(css = "[class='icon icon_less js-calendar_prev']")
    private WebElement previousMonthButton;

    @FindBy(css = "[class='calendar__control calendar__control_next']")
    private WebElement nextMonthButton;

    @FindBy(css = "[class='calendar__cell calendar__cell_enabled']")
    private List<WebElement> dayButtonList;

    @FindBy(css = "[class='heading heading_minor heading_line']")
    private WebElement header;

    private String headerSelector = header.toString();
    private String monthSelector = month.toString();

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
    public WeatherCalendarPage pressPreviousCalendarButton(){
        checkCalendarToBeOpened();

        String monthBeforeString = month.getText();

        previousMonthButton.click();

        assertTrue("Месяц изменился", standartWaiter.
                waitForCondition(ExpectedConditions.not(
                        ExpectedConditions.textToBe(
                                By.cssSelector(monthSelector), monthBeforeString))));
        assertTrue("Месяц поменялся на предыдущий",
                Month.checkMonthChangesToPrevious(
                        monthBeforeString, month.getText()));
        return this;
    }
    public WeatherCalendarPage pressNextCalendarButton(){
        checkCalendarToBeOpened();

        String monthBeforeString = month.getText();

        nextMonthButton.click();

        assertTrue("Месяц изменился", standartWaiter.
                waitForCondition(ExpectedConditions.not(
                        ExpectedConditions.textToBe(
                                By.cssSelector(monthSelector), monthBeforeString))));
        assertTrue("Месяц поменялся на следующий",
                Month.checkMonthChangesToNext(
                        monthBeforeString, month.getText()));
        return this;
    }
    public WeatherCalendarPage pressDayButton(Integer day){
        checkCalendarToBeOpened();

        String monthString = month.getText();
        monthString = monthString.substring(0, monthString.length() - 1);
        String dayString = Integer.toString(day);

        dayButtonList.stream()
                            .filter(calendarDay -> Integer.parseInt(calendarDay.getText()) == day)
                            .findFirst()
                            .get()
                            .click();
        assertTrue("переход на другую страницу", standartWaiter.
                waitForCondition(ExpectedConditions.invisibilityOf(calendar)));
        assertTrue("Показана страница с нужной датой", header.getText().
                contains(dayString + " " + monthString.toUpperCase()));
        return this;
    }
}
