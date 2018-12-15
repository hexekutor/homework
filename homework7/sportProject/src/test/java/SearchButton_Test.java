import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class SearchButton_Test {
    @Test
    @DisplayName("Проверка кнопки поиска")
    public void checkSearchButton() {
        new MainPage()
                .open()
                .checkSearchButton();
    }
}
