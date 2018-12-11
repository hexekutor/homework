import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class clickUserAvatar_Test {
    @Test
    @DisplayName("Проверка открытия профиля пользователя")
    public void checkPaginationView() {
        new MainPage()
                .open()
                .pressUserAvatar();
    }
}
