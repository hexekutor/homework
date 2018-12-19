import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class CheckNewsOpen_Test {

    @Test
    @DisplayName("Проверка кнопок шардинга")
    public void checkNewsOpen() {
        new MainPage()
                .open()
                .pressNews()
                .checkNewsTitle()
                .checkUrl();

    }
}
