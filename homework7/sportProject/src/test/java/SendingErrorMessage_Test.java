import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class SendingErrorMessage_Test {
    @Test
    @DisplayName("Проверка сообщение об ошибке")
    public void checkMistakePopup() {
        new MainPage()
                .open()
                .pressNews()
                .selectText()
                .checkPopup();
    }
}
