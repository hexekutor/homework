import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class CheckFlowerHoroscope_Test {

    @Test
    @DisplayName("Проверка открытия цветочного гороскопа")
    public void checkColorHoroscope() {
        new MainPage("Цветочный гороскоп")
                .open()
                .clickHoroscope()
                .checkHoroscopeTitle();

    }
}
