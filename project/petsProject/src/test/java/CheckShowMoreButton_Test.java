import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class CheckShowMoreButton_Test {

    @Test
    @DisplayName("Проверка кнопки \"Показать еще\"")
    public void checkShowMoreButton() {
        new MainPage()
                .open()
                .clickShowMoreButton()
                .checkShowMoreButton();

    }
}
