import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class MenuSelectCity_Test {

    @Test
    @DisplayName("Проверка кнопки \"Показать еще\"")
    public void checkMenuSelectCity() {
        new MainPage()
                .open()
                .checkMenu()
                .checkCityUrl();

    }
}
